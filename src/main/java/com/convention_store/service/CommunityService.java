package com.convention_store.service;

import com.convention_store.dto.PostDto;
import com.convention_store.repository.CommunityRepository;
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

}
