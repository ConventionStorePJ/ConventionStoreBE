package com.convention_store.repository;

import com.convention_store.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByPostIdAndFingerprint(Long postId, String fingerprint);

    long countByPostId(Long postId);
}
