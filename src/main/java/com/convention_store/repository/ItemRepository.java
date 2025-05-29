package com.convention_store.repository;

import com.convention_store.domain.Item;
import com.convention_store.domain.enums.DiscountType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    
    @Query("SELECT i FROM Item i "
        + "LEFT JOIN Discount d ON d.item.id = i.id "
        + "WHERE (:keyword IS NULL OR (i.itemName LIKE CONCAT('%', :keyword, '%'))) "
        + "AND (:franchiseId IS NULL OR i.franchise.id = :franchiseId) "
        + "AND (:discountType IS NULL OR (d.id IS NOT NULL AND d.discountType = :discountType AND d.endDate >= CURRENT_TIMESTAMP)) ")
    List<Item> findByItemNameAndFranchiseIdAndDiscountType(
        @Param("keyword") String keyword,
        @Param("franchiseId") Long franchiseId,
        @Param("discountType") DiscountType discountType
    );
}
