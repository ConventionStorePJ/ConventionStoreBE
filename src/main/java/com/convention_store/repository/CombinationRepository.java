package com.convention_store.repository;

import com.convention_store.domain.Combination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombinationRepository extends JpaRepository<Combination, Long> {
}
