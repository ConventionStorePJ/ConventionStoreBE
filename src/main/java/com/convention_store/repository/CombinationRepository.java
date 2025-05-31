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
    
    @Query("SELECT c FROM Combination c "
        + "WHERE (:franchiseId IS NULL OR c.franchise.id = :franchiseId) "
        + "AND (:category IS NULL OR c.category = :category)"
        + "ORDER BY c.likeCount DESC")
    List<Combination> findByCategoryAndFranchiseIdOrderByLikeCountDesc(
        @Param("franchiseId") Long franchiseId,
        @Param("category") CombinationCategoryType category
    );
    
    // TODO: JPQL 에서 SELECT 해오는 값은 별칭이 필수 : 엔티티 자체를 선택하려면 해당 엔티티의 별칭(c)을 사용해야 합니다.
    @Query("SELECT c FROM Combination c "
        + "WHERE (:franchiseId IS NULL OR (c.franchise.id = :franchiseId)) "
        + "AND (:category IS NULL OR (c.category = :category))"
        + "ORDER BY c.createdAt DESC")
    List<Combination> findByCategoryAndFranchiseIdOrderByCreateAtDesc(
        @Param("franchiseId") Long franchiseId,
        @Param("category") CombinationCategoryType category
    );
}
