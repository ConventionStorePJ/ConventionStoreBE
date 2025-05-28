package com.convention_store.controller;

import com.convention_store.dto.PostDetailDto;
import com.convention_store.dto.PostDto;
import com.convention_store.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "커뮤니티", description = "커뮤니티 관련 API")
@RequestMapping("/api")
@RestController
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Operation(
            summary = "전체 게시글 조회",
            description = "등록된 모든 커뮤니티 게시글 목록을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상적으로 게시글 목록 반환"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = communityService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @Operation(
            summary = "특정 게시글 조회",
            description = "클릭한 게시글 정보를 (댓글포함) 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "정상적으로 게시글 정보 반환"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailDto> getPost(@PathVariable Long postId){
        PostDetailDto post = communityService.getPost(postId);
        return ResponseEntity.ok(post);
    }
}
