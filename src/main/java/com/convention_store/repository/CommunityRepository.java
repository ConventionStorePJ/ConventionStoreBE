package com.convention_store.repository;

import com.convention_store.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Post, Long> {
    @Query("""
        SELECT p
        FROM Post p
        LEFT JOIN FETCH p.franchise f
        LEFT JOIN FETCH p.combination c
        ORDER BY p.likeCount DESC
    """)
    List<Post> findPopularPosts();

    @Query("""
        SELECT p
        FROM Post p
        LEFT JOIN FETCH p.franchise f
        LEFT JOIN FETCH p.combination c
        WHERE p.combination IS NOT NULL
        ORDER BY p.createdAt DESC
    """)
    List<Post> findCombinationPosts();

    @Query("""
        SELECT p
        FROM Post p
        LEFT JOIN FETCH p.franchise f
        WHERE p.combination IS NULL
        ORDER BY p.createdAt DESC
    """)
    List<Post> findNormalPosts();

    List<Post> findAllByOrderByLikeCountDesc();
}
