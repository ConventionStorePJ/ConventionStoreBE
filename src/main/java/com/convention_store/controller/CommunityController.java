package com.convention_store.controller;

import com.convention_store.dto.*;
import com.convention_store.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "게시글 생성", description = "새 게시글을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "게시글 생성 성공"),
            @ApiResponse(responseCode = "400", description = "입력값 오류"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateDto request) {
        PostDto created = communityService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @Operation(summary = "게시글 댓글 등록", description = "해당 게시글에 댓글을 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "댓글 등록 성공"),
            @ApiResponse(responseCode = "404", description = "게시글이 존재하지 않음")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long postId,
            @RequestBody CommentCreateDto request
    ) {
        CommentDto saved = communityService.createComment(postId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @PutMapping("/posts/{postId}")
    @Operation(summary = "게시글 수정", description = "비밀번호 검증 후 게시글 제목/내용을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(schema = @Schema(implementation = PostDto.class))),
    })
    public ResponseEntity<PostDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateDto updateDto
    ) {
        PostDto updated = communityService.updatePost(postId, updateDto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/posts/{postId}")
    @Operation(summary = "게시글 삭제", description = "비밀번호 검증 후 게시글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "삭제 성공")
    })
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @RequestBody PasswordCheckDto dto
    ) {
        communityService.deletePost(postId, dto.getPasswordHash());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    @Operation(summary = "댓글 삭제", description = "비밀번호 검증 후 댓글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "댓글 삭제 성공")
    })
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestBody PasswordCheckDto dto
    ) {
        communityService.deleteComment(commentId, dto.getPasswordHash());
        return ResponseEntity.noContent().build();
    }






}
