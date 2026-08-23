package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface DailyTestCompletionRepository
        extends JpaRepository<DailyTestCompletionEntity, DailyTestCompletionId> {

    interface CompletionTotal {
        String getTestId();
        long getTotalCompletions();
    }

    List<DailyTestCompletionEntity> findAllByCompletionDate(LocalDate completionDate);

    List<DailyTestCompletionEntity> findAllByTestIdAndCompletionDateBetweenOrderByCompletionDateAsc(
            String testId, LocalDate startDate, LocalDate endDate);

    @Query("select completion.testId as testId, "
            + "sum(completion.completionCount) as totalCompletions "
            + "from DailyTestCompletionEntity completion group by completion.testId")
    List<CompletionTotal> findCompletionTotals();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update DailyTestCompletionEntity completion "
            + "set completion.completionCount = completion.completionCount + 1 "
            + "where completion.testId = :testId "
            + "and completion.completionDate = :completionDate")
    int increment(@Param("testId") String testId,
                  @Param("completionDate") LocalDate completionDate);
}
