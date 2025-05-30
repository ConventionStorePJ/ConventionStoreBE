package com.convention_store.service;

import com.convention_store.domain.*;
import com.convention_store.dto.*;
import com.convention_store.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private CombinationRepository combinationRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    // 전체 게시글 가져오기
    public List<PostDto> getAllPosts() {
        return communityRepository.findAll()
                .stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    // 특정 게시글 가져오기(댓글포함)
    public PostDetailDto getPost(Long postId){
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        return PostDetailDto.from(post);
    }

    // 게시글 생성하기
    public PostDto createPost(PostCreateDto request) {
        Franchise franchise = franchiseRepository.findById(request.getFranchiseId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 프랜차이즈입니다."));

        Combination combination = null;
        if (request.getCombinationId() != null) {
            combination = combinationRepository.findById(request.getCombinationId())
                    .orElse(null); // 없어도 null 처리
        }

        Post post = request.toEntity(franchise, combination);
        Post saved = communityRepository.save(post);

        return PostDto.from(saved);
    }

    // 댓글 생성하기
    public CommentDto createComment(Long postId, CommentCreateDto request) {
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        Comment comment = request.toEntity(post);
        commentRepository.save(comment);
        return CommentDto.from(comment);
    }

    // 게시글 수정
    public PostDto updatePost(Long postId, PostUpdateDto dto) {
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));
        validatePassword(dto.getPasswordHash(), post.getPasswordHash());
        post.updatePost(dto.getTitle(), dto.getContent(), post.getAuthorName(), post.getPasswordHash());
        return PostDto.from(post);
    }

    // 게시글 삭제
    public void deletePost(Long postId, String passwordHash) {
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));
        validatePassword(passwordHash, post.getPasswordHash());
        communityRepository.delete(post);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId, String passwordHash) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글 없음"));
        validatePassword(passwordHash, comment.getPasswordHash());
        commentRepository.delete(comment);
    }

    // 비밀번호 검증
    private void validatePassword(String input, String actual) {
        if (!Objects.equals(input, actual)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    // 좋아요
    @Transactional
    public LikeDto toggleLike(Long postId, HttpServletRequest request) {
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));

        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String fingerprint = ip + ":" + userAgent;

        Optional<Like> existingLike = likeRepository.findByPostIdAndFingerprint(postId, fingerprint);

        String message;
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            message = "좋아요 취소됨";
        } else {
            Like like = Like.builder()
                    .post(post)
                    .fingerprint(fingerprint)
                    .build();
            likeRepository.save(like);
            message = "좋아요 등록됨";
        }

        communityRepository.save(post);

        return LikeDto.builder()
                .message(message)
                .likeCount(post.getLikeCount())
                .build();
    }



}
