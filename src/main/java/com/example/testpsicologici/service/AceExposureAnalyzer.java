package com.example.testpsicologici.service;

import com.example.testpsicologici.model.AceExposureResult;
import com.example.testpsicologici.model.AceGroupResult;
import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestQuestion;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/** Restituzione categoriale ACE, separata dagli score ordinali degli altri questionari. */
@Service
public class AceExposureAnalyzer {

    public static final String SCORING_MODEL = "ACE_EXPOSURE";
    public static final String ANSWER_SCALE = "ACE_PRESENCE";
    public static final int YES = 1;
    public static final int NO = 2;
    public static final int UNKNOWN = 3;
    public static final int SKIPPED = 4;

    private static final List<Category> CATEGORIES = List.of(
            new Category("A", List.of("A01", "A02")),
            new Category("A", List.of("A03", "A04")),
            new Category("A", List.of("A05", "A06")),
            new Category("B", List.of("B01", "B02", "B03", "B04")),
            new Category("B", List.of("B05", "B06")),
            new Category("C", List.of("C01", "C02")),
            new Category("C", List.of("C03")),
            new Category("C", List.of("C04")),
            new Category("C", List.of("C05")),
            new Category("C", List.of("C06")),
            new Category("D", List.of("D01", "D02")),
            new Category("D", List.of("D03", "D04")),
            new Category("D", List.of("D05", "D06")));
    private static final Set<String> INDICATOR_CODES = CATEGORIES.stream()
            .flatMap(category -> category.indicatorCodes().stream())
            .collect(Collectors.toUnmodifiableSet());

    public AceExposureResult analyze(PsychologicalTest test, TestAttempt attempt) {
        if (!SCORING_MODEL.equals(test.scoringModel()) || !ANSWER_SCALE.equals(test.answerScale())
                || test.scoreVisible()) {
            throw new IllegalArgumentException("Modello ACE non configurato");
        }
        if (test.questions().size() != INDICATOR_CODES.size()
                || !test.areas().stream().map(area -> area.code()).toList().equals(List.of("A", "B", "C", "D"))) {
            throw new IllegalStateException("Struttura ACE incompleta");
        }

        Map<String, Integer> answers = new HashMap<>();
        for (int index = 0; index < test.questions().size(); index++) {
            TestQuestion question = test.questions().get(index);
            String indicatorCode = question.indicatorCode();
            if (indicatorCode == null || !INDICATOR_CODES.contains(indicatorCode)
                    || !indicatorCode.startsWith(question.areaCode())
                    || answers.putIfAbsent(indicatorCode, attempt.answerAt(index)) != null) {
                throw new IllegalStateException("Indicatore ACE assente, duplicato o fuori area");
            }
            if (attempt.answerAt(index) < YES || attempt.answerAt(index) > SKIPPED) {
                throw new IllegalArgumentException("Risposta ACE non valida");
            }
        }
        if (!answers.keySet().equals(INDICATOR_CODES)) {
            throw new IllegalStateException("Mappa degli indicatori ACE incompleta");
        }
        boolean partial = answers.values().stream().anyMatch(answer -> answer == UNKNOWN || answer == SKIPPED);

        Map<String, List<Status>> categoriesByGroup = CATEGORIES.stream()
                .collect(Collectors.groupingBy(Category::groupCode,
                        java.util.LinkedHashMap::new,
                        Collectors.mapping(category -> status(category.indicatorCodes().stream()
                                .map(answers::get).toList()), Collectors.toList())));
        List<AceGroupResult> groups = test.areas().stream()
                .map(area -> {
                    List<Status> categoryStatuses = categoriesByGroup.get(area.code());
                    if (categoryStatuses == null) {
                        throw new IllegalStateException("Gruppo ACE non configurato: " + area.code());
                    }
                    return new AceGroupResult(area.code(), area.name(), statusOfStatuses(categoryStatuses).name());
                })
                .toList();
        long presentGroups = groups.stream().filter(group -> Status.PRESENT.name().equals(group.status())).count();
        String profileCode = presentGroups > 1 ? "MULTIPLE_GROUPS"
                : presentGroups == 1 ? "ONE_GROUP"
                : partial ? "UNDETERMINED" : "NONE_REPORTED";
        return new AceExposureResult(profileCode, partial, groups);
    }

    private Status status(List<Integer> answers) {
        if (answers.stream().anyMatch(answer -> answer == YES)) return Status.PRESENT;
        if (answers.stream().allMatch(answer -> answer == NO)) return Status.NOT_REPORTED;
        return Status.UNDETERMINED;
    }

    private Status statusOfStatuses(List<Status> statuses) {
        if (statuses.stream().anyMatch(status -> status == Status.PRESENT)) return Status.PRESENT;
        if (statuses.stream().allMatch(status -> status == Status.NOT_REPORTED)) return Status.NOT_REPORTED;
        return Status.UNDETERMINED;
    }

    private enum Status { PRESENT, NOT_REPORTED, UNDETERMINED }

    private record Category(String groupCode, List<String> indicatorCodes) { }
}
