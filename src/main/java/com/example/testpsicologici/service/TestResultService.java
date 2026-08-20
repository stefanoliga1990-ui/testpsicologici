package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.AreaResult;
import com.example.testpsicologici.model.TestArea;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultService {

    static final double LOW_LIMIT = 2.5;
    static final double HIGH_LIMIT = 3.5;

    private final TestCatalogue catalogue;

    public TestResultService(TestCatalogue catalogue) {
        this.catalogue = catalogue;
    }

    public TestResult analyze(PsychologicalTest test, TestAttempt attempt) {
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
                areaResults);
    }

    String profileCode(long highAreaCount, long lowAreaCount, int areaCount) {
        if (highAreaCount >= 3) return "BROAD";
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
}
