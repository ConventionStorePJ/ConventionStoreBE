package com.convention_store.service;

import com.convention_store.domain.Post;
import com.convention_store.dto.PostDetailDto;
import com.convention_store.dto.PostDto;
import com.convention_store.repository.CommunityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    // 전체 게시글 가져오기
    public List<PostDto> getAllPosts() {
        return communityRepository.findAll()
                .stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    //특정 게시글 가져오기(댓글포함)
    public PostDetailDto getPost(Long postId){
        Post post = communityRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        return PostDetailDto.from(post);
    }

}
