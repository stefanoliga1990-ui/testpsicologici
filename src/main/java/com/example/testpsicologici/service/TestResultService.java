package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.AreaResult;
import com.example.testpsicologici.model.AttachmentStyleResult;
import com.example.testpsicologici.model.TestArea;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Comparator;
import java.util.Set;

@Service
public class TestResultService {

    static final double LOW_LIMIT = 2.5;
    static final double HIGH_LIMIT = 3.5;
    static final double ATTACHMENT_STYLE_MARGIN = 0.08;
    static final double ATTACHMENT_MULTIPLE_SPREAD = 0.05;

    private static final List<AttachmentPrototype> ATTACHMENT_PROTOTYPES = List.of(
            new AttachmentPrototype("SECURE", "Orientamento sicuro", 0, 0),
            new AttachmentPrototype("ANXIOUS_PREOCCUPIED", "Orientamento ansioso-preoccupato", 1, 0),
            new AttachmentPrototype("DISMISSING_AVOIDANT", "Orientamento evitante-distanziante", 0, 1),
            new AttachmentPrototype("FEARFUL_AVOIDANT", "Orientamento timoroso-evitante", 1, 1));

    private final TestCatalogue catalogue;

    public TestResultService(TestCatalogue catalogue) {
        this.catalogue = catalogue;
    }

    public TestResult analyze(PsychologicalTest test, TestAttempt attempt) {
        if ("ATTACHMENT_DIMENSIONAL".equals(test.scoringModel())) {
            return analyzeAttachment(test, attempt);
        }

        List<AreaScore> areaScores = test.areas().stream()
                .map(area -> new AreaScore(area, scoreForArea(test, attempt, area.code())))
                .toList();

        double overallAverage = (double) attempt.score() / test.questions().size();
        long highAreaCount = areaScores.stream().filter(area -> area.score() >= HIGH_LIMIT).count();
        long lowAreaCount = areaScores.stream().filter(area -> area.score() < LOW_LIMIT).count();
        String profileCode = profileCode(highAreaCount, lowAreaCount, areaScores.size());
        long areaResultLimit = test.scoreVisible() ? 2 : test.areas().size();
        List<AreaResult> areaResults = test.areas().size() > 1
                ? areaScores.stream()
                .limit(areaResultLimit)
                .map(this::toAreaResult)
                .toList()
                : List.of();

        int percentage = Math.round((float) (overallAverage - 1) / 4 * 100);
        return new TestResult(
                attempt.score(), percentage,
                catalogue.findGlobalInterpretation(test.id(), profileCode),
                areaResults,
                List.of());
    }

    private TestResult analyzeAttachment(PsychologicalTest test, TestAttempt attempt) {
        List<AreaScore> dimensionScores = test.areas().stream()
                .map(area -> new AreaScore(area, scoreForArea(test, attempt, area.code())))
                .toList();
        double anxiety = normalizedScore(dimensionScores, "ansia");
        double avoidance = normalizedScore(dimensionScores, "evitamento");

        List<StyleDistance> orderedStyles = ATTACHMENT_PROTOTYPES.stream()
                .map(prototype -> new StyleDistance(prototype, closeness(anxiety, avoidance, prototype)))
                .sorted(Comparator.comparingDouble(StyleDistance::closeness).reversed())
                .toList();
        String profileCode = attachmentProfileCode(orderedStyles);
        List<AttachmentStyleResult> styleResults = java.util.stream.IntStream.range(0, orderedStyles.size())
                .mapToObj(index -> {
                    AttachmentPrototype prototype = orderedStyles.get(index).prototype();
                    return new AttachmentStyleResult(
                            index + 1,
                            prototype.code(),
                            prototype.title(),
                            catalogue.findStyleInterpretation(test.id(), prototype.code()));
                })
                .toList();

        return new TestResult(
                attempt.score(),
                0,
                catalogue.findGlobalInterpretation(test.id(), profileCode),
                dimensionScores.stream().map(this::toAreaResult).toList(),
                styleResults);
    }

    private double normalizedScore(List<AreaScore> scores, String code) {
        double mean = scores.stream()
                .filter(score -> score.area().code().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Dimensione non configurata: " + code))
                .score();
        return (mean - 1) / 4;
    }

    private double closeness(double anxiety, double avoidance, AttachmentPrototype prototype) {
        double distance = Math.sqrt(
                Math.pow(anxiety - prototype.anxiety(), 2)
                        + Math.pow(avoidance - prototype.avoidance(), 2));
        return 1 - distance / Math.sqrt(2);
    }

    private String attachmentProfileCode(List<StyleDistance> orderedStyles) {
        double spread = orderedStyles.get(0).closeness()
                - orderedStyles.get(orderedStyles.size() - 1).closeness();
        if (spread <= ATTACHMENT_MULTIPLE_SPREAD) return "INTERMEDIATE_MULTIPLE";

        double firstSecondDifference = orderedStyles.get(0).closeness() - orderedStyles.get(1).closeness();
        if (firstSecondDifference >= ATTACHMENT_STYLE_MARGIN) {
            return orderedStyles.get(0).prototype().code();
        }
        return intermediateProfileCode(
                orderedStyles.get(0).prototype().code(),
                orderedStyles.get(1).prototype().code());
    }

    private String intermediateProfileCode(String first, String second) {
        Set<String> pair = Set.of(first, second);
        if (pair.equals(Set.of("SECURE", "ANXIOUS_PREOCCUPIED"))) return "INTERMEDIATE_SECURE_ANXIOUS";
        if (pair.equals(Set.of("SECURE", "DISMISSING_AVOIDANT"))) return "INTERMEDIATE_SECURE_DISMISSING";
        if (pair.equals(Set.of("ANXIOUS_PREOCCUPIED", "FEARFUL_AVOIDANT"))) return "INTERMEDIATE_ANXIOUS_FEARFUL";
        if (pair.equals(Set.of("DISMISSING_AVOIDANT", "FEARFUL_AVOIDANT"))) return "INTERMEDIATE_DISMISSING_FEARFUL";
        return "INTERMEDIATE_MULTIPLE";
    }

    String profileCode(long highAreaCount, long lowAreaCount, int areaCount) {
        long broadThreshold = Math.max(2, areaCount - 1L);
        if (highAreaCount >= broadThreshold) return "BROAD";
        if (highAreaCount >= 1) return "FOCUSED";
        if (lowAreaCount == areaCount) return "LOW";
        return "MIXED";
    }

    private double scoreForArea(PsychologicalTest test, TestAttempt attempt, String areaCode) {
        int total = 0;
        int count = 0;
        for (int index = 0; index < test.questions().size(); index++) {
            if (test.questions().get(index).areaCode().equals(areaCode)) {
                total += attempt.answerAt(index);
                count++;
            }
        }
        if (count == 0) throw new IllegalStateException("Area senza domande: " + areaCode);
        return (double) total / count;
    }

    private String insightFor(TestArea area, double score) {
        if (score < LOW_LIMIT) return area.lowInsight();
        if (score < HIGH_LIMIT) return area.mediumInsight();
        return area.highInsight();
    }

    private AreaResult toAreaResult(AreaScore areaScore) {
        int percentage = (int) Math.round((areaScore.score() - 1) / 4 * 100);
        return new AreaResult(
                areaScore.area().code(),
                areaScore.area().name(),
                insightFor(areaScore.area(), areaScore.score()),
                percentage);
    }

    private record AreaScore(TestArea area, double score) {
    }

    private record AttachmentPrototype(String code, String title, double anxiety, double avoidance) {
    }

    private record StyleDistance(AttachmentPrototype prototype, double closeness) {
    }
}
