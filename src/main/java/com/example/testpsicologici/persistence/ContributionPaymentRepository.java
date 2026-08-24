package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContributionPaymentRepository
        extends JpaRepository<ContributionPaymentEntity, String> {
}
