package com.convention_store.repository;

import com.convention_store.domain.Combination;
import com.convention_store.domain.enums.CombinationCategoryType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Long> {
    
    @Query("SELECT Combination FROM Combination c "
        + "WHERE :franchiseId IS NOT NULL OR (:franchiseId = c.franchise.id) "
        + "AND :category IS NOT NULL OR (:category = c.category) "
        + "ORDER BY c.likeCount DESC")
    List<Combination> findByCategoryAndFranchiseIdOrderByLikeCountDesc(
        @Param("franchiseId") Long franchiseId,
        @Param("category") CombinationCategoryType category
    );
    
    @Query("SELECT Combination FROM Combination c "
        + "WHERE :franchiseId IS NOT NULL OR (:franchiseId = c.franchise.id) "
        + "AND :category IS NOT NULL OR (:category = c.category) "
        + "ORDER BY c.createdAt DESC")
    List<Combination> findByCategoryAndFranchiseIdOrderByCreateAtDesc(
        @Param("franchiseId") Long franchiseId,
        @Param("category") CombinationCategoryType category
    );
}
