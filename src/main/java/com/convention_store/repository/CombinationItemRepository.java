package com.convention_store.repository;

import com.convention_store.domain.CombinationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationItemRepository extends JpaRepository<CombinationItem, Long> {

}
