package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PsychometricStructureTest {

    @Autowired
    private TestCatalogue catalogue;

    @Autowired
    private TestResultService resultService;

    @Test
    void everyQuestionnaireHasACompleteBalancedAndInterleavedBlueprint() {
        assertThat(catalogue.findAll()).hasSize(15).allSatisfy(test -> {
            assertThat(test.responseInstruction()).isNotBlank().containsIgnoringCase("frequenza");
            assertThat(test.areas()).hasSize(4);
            assertThat(test.questions()).hasSize(24);
            assertThat(new HashSet<>(test.questions())).hasSize(24);

            test.areas().forEach(area -> assertThat(test.questions())
                    .filteredOn(question -> question.areaCode().equals(area.code()))
                    .hasSize(6));

            for (int index = 1; index < test.questions().size(); index++) {
                assertThat(test.questions().get(index).areaCode())
                        .as("Gli item consecutivi di %s devono appartenere ad aree diverse", test.id())
                        .isNotEqualTo(test.questions().get(index - 1).areaCode());
            }
        });
    }

    @Test
    void descriptiveProfilesDependOnAreaPatternRatherThanAnOpaqueOverallCutoff() {
        assertThat(resultService.profileCode(0, 4, 4)).isEqualTo("LOW");
        assertThat(resultService.profileCode(0, 3, 4)).isEqualTo("MIXED");
        assertThat(resultService.profileCode(1, 3, 4)).isEqualTo("FOCUSED");
        assertThat(resultService.profileCode(3, 1, 4)).isEqualTo("BROAD");
    }

    @Test
    void descriptiveThresholdsAreAlignedWithResponseCategoryMidpoints() {
        assertThat(TestResultService.LOW_LIMIT).isEqualTo(2.5);
        assertThat(TestResultService.HIGH_LIMIT).isEqualTo(3.5);
    }
}
