package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TopicClusterCatalogueTest {

    @Autowired
    private TopicClusterCatalogue topicClusterCatalogue;

    @Autowired
    private TestCatalogue testCatalogue;

    @Test
    void everyPublishedTestBelongsToExactlyOneCluster() {
        List<String> activeTestIds = testCatalogue.findAll().stream()
                .map(PsychologicalTest::id)
                .toList();
        List<String> clusteredTestIds = topicClusterCatalogue.findAll().stream()
                .flatMap(cluster -> cluster.testIds().stream())
                .toList();

        assertThat(clusteredTestIds).doesNotHaveDuplicates();
        assertThat(clusteredTestIds).containsExactlyInAnyOrderElementsOf(activeTestIds);
    }

    @Test
    void relatedTestsStayInTheSameClusterWithoutIncludingTheCurrentTest() {
        for (PsychologicalTest test : testCatalogue.findAll()) {
            var cluster = topicClusterCatalogue.findByTestId(test.id()).orElseThrow();
            var relatedIds = topicClusterCatalogue.findRelatedTestIds(test.id(), 3);

            assertThat(relatedIds)
                    .doesNotContain(test.id())
                    .doesNotHaveDuplicates()
                    .allMatch(cluster.testIds()::contains)
                    .hasSize(Math.min(3, cluster.testIds().size() - 1));
        }
    }

    @Test
    void relationalAmbiguityTestsBelongToTheirDedicatedCluster() {
        var cluster = topicClusterCatalogue.findByTestId("gaslighting").orElseThrow();

        assertThat(cluster.slug()).isEqualTo("ambiguita-e-manipolazione-relazionale");
        assertThat(cluster.title()).isEqualTo("Ambiguità e manipolazione relazionale");
        assertThat(cluster.testIds()).containsExactly("relazione-dannosa-benessere", "gaslighting", "love-bombing", "breadcrumbing", "orbiting", "hoovering");
        assertThat(topicClusterCatalogue.findByTestId("relazione-dannosa-benessere")).contains(cluster);
        assertThat(topicClusterCatalogue.findByTestId("breadcrumbing")).contains(cluster);
        assertThat(topicClusterCatalogue.findByTestId("love-bombing")).contains(cluster);
        assertThat(topicClusterCatalogue.findByTestId("orbiting")).contains(cluster);
        assertThat(topicClusterCatalogue.findByTestId("hoovering")).contains(cluster);
        assertThat(topicClusterCatalogue.findByTestId("limerenza").orElseThrow().testIds())
                .doesNotContain("relazione-dannosa-benessere", "gaslighting", "love-bombing", "breadcrumbing", "orbiting", "hoovering");
        assertThat(topicClusterCatalogue.findRelatedTestIds("relazione-dannosa-benessere", 3))
                .containsExactly("gaslighting", "hoovering", "love-bombing");
    }

    @Test
    void coupleCompatibilityBelongsToRelationshipsAndUsesNearbyRelationalSuggestions() {
        var cluster = topicClusterCatalogue.findByTestId("compatibilita-coppia").orElseThrow();

        assertThat(cluster.slug()).isEqualTo("relazioni-e-attaccamento");
        assertThat(cluster.title()).isEqualTo("Relazioni e attaccamento");
        assertThat(topicClusterCatalogue.findRelatedTestIds("compatibilita-coppia", 3))
                .containsExactly("gelosia-partner", "dipendenza-affettiva", "dinamiche-narcisistiche-partner");
    }
}
