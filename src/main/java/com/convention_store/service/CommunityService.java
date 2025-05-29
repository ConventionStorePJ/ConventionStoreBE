package com.convention_store.service;

import com.convention_store.domain.Combination;
import com.convention_store.domain.Comment;
import com.convention_store.domain.Franchise;
import com.convention_store.domain.Post;
import com.convention_store.dto.*;
import com.convention_store.repository.CombinationRepository;
import com.convention_store.repository.CommentRepository;
import com.convention_store.repository.CommunityRepository;
import com.convention_store.repository.FranchiseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    //게시글 삭제
    public void deletePost(Long postId, String passwordHash) {
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시글 없음"));
        validatePassword(passwordHash, post.getPasswordHash());
        communityRepository.delete(post);
    }

    //댓글 삭제


    //비밀번호 검증
    private void validatePassword(String input, String actual) {
        if (!Objects.equals(input, actual)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }



}
