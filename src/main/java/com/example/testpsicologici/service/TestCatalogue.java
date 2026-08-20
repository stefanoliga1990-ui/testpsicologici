package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.ResultContent;
import com.example.testpsicologici.model.TestArea;
import com.example.testpsicologici.model.TestQuestion;
import com.example.testpsicologici.model.TestReference;
import com.example.testpsicologici.persistence.InterpretationEntity;
import com.example.testpsicologici.persistence.InterpretationRepository;
import com.example.testpsicologici.persistence.TestAreaRepository;
import com.example.testpsicologici.persistence.TestDefinitionEntity;
import com.example.testpsicologici.persistence.TestDefinitionRepository;
import com.example.testpsicologici.persistence.TestQuestionRepository;
import com.example.testpsicologici.persistence.TestReferenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TestCatalogue {

    private final TestDefinitionRepository testRepository;
    private final TestAreaRepository areaRepository;
    private final TestQuestionRepository questionRepository;
    private final TestReferenceRepository referenceRepository;
    private final InterpretationRepository interpretationRepository;
    private final ReferenceContributionCatalogue referenceContributionCatalogue;

    public TestCatalogue(TestDefinitionRepository testRepository, TestAreaRepository areaRepository,
                         TestQuestionRepository questionRepository, TestReferenceRepository referenceRepository,
                         InterpretationRepository interpretationRepository,
                         ReferenceContributionCatalogue referenceContributionCatalogue) {
        this.testRepository = testRepository;
        this.areaRepository = areaRepository;
        this.questionRepository = questionRepository;
        this.referenceRepository = referenceRepository;
        this.interpretationRepository = interpretationRepository;
        this.referenceContributionCatalogue = referenceContributionCatalogue;
    }

    public List<PsychologicalTest> findAll() {
        return testRepository.findByActiveTrueOrderByDisplayOrderAsc().stream().map(this::toModel).toList();
    }

    public PsychologicalTest findById(String id) {
        TestDefinitionEntity entity = testRepository.findById(id)
                .filter(TestDefinitionEntity::isActive)
                .orElseThrow(() -> new IllegalArgumentException("Test non disponibile"));
        return toModel(entity);
    }

    public ResultContent findGlobalInterpretation(String testId, String profileCode) {
        return interpretationRepository.findByTestIdAndScopeAndAreaCodeIsNullAndCode(testId, "GLOBAL", profileCode)
                .map(this::toResultContent)
                .orElseThrow(() -> new IllegalStateException("Analisi generale non configurata: " + profileCode));
    }

    private PsychologicalTest toModel(TestDefinitionEntity entity) {
        List<TestArea> areas = areaRepository.findByTestIdOrderByDisplayOrderAsc(entity.getId()).stream()
                .map(area -> new TestArea(
                        area.getCode(), area.getInternalName(),
                        areaInsight(entity.getId(), area.getCode(), "LOW"),
                        areaInsight(entity.getId(), area.getCode(), "MEDIUM"),
                        areaInsight(entity.getId(), area.getCode(), "HIGH")))
                .toList();
        List<TestQuestion> questions = questionRepository.findByTestIdOrderByPositionAsc(entity.getId()).stream()
                .map(question -> new TestQuestion(question.getText(), question.getAreaCode()))
                .toList();
        List<TestReference> references = referenceRepository.findByTestIdOrderByDisplayOrderAsc(entity.getId()).stream()
                .map(reference -> new TestReference(reference.getTitle(), reference.getUrl(),
                        referenceContributionCatalogue.findByUrl(reference.getUrl())))
                .toList();
        return new PsychologicalTest(
                entity.getId(), entity.getTitle(), entity.getSeoTitle(), entity.getEyebrow(),
                entity.getDescription(), entity.getSeoDescription(), entity.getDuration(),
                entity.getIntroductoryText(), entity.getResponseInstruction(), entity.getVersion(), entity.isScoreVisible(),
                entity.getOverallMetricLabel(), entity.getAreaMetricLabel(), areas, questions, references);
    }

    private String areaInsight(String testId, String areaCode, String level) {
        return interpretationRepository.findByTestIdAndScopeAndAreaCodeAndCode(testId, "AREA", areaCode, level)
                .map(InterpretationEntity::getDescription)
                .orElse("");
    }

    private ResultContent toResultContent(InterpretationEntity entity) {
        return new ResultContent(entity.getTitle(), entity.getDescription(), entity.getDetail());
    }
}
