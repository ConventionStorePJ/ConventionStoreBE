package com.convention_store.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Like extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "fingerprint", nullable = false, length = 512)
    private String fingerprint;

    @Builder
    public Like(Post post, String fingerprint) {
        this.post = post;
        this.fingerprint = fingerprint;
    }
}
