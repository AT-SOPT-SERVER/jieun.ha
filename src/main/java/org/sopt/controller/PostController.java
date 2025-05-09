package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.dto.base.BaseResponse;
import org.sopt.dto.request.PostCreateRequest;
import org.sopt.dto.request.PostTitleUpdateRequest;
import org.sopt.dto.response.PostListResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/board")
public class PostController {
    private final PostService postService;

    protected PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public BaseResponse<?> createPost(
            @RequestHeader Long userId,
            @RequestBody final PostCreateRequest postCreateRequest
    ) {
        postService.createPost(userId, postCreateRequest);
        return BaseResponse.success(SuccessMessage.CREATED);
    }

    @GetMapping()
    public BaseResponse<PostListResponse> getAllPosts(
            @RequestHeader Long userId
    ) {
        return BaseResponse.success(SuccessMessage.OK, postService.getAllPost(userId));
    }

    @GetMapping("/{post-id}")
    public BaseResponse<PostResponse> getPostById(
            @RequestHeader Long userId,
            @PathVariable("post-id") Long postId
    ) {
        return BaseResponse.success(SuccessMessage.OK, postService.getPostById(userId, postId));
    }

    @PatchMapping("/{post-id}")
    public BaseResponse<PostResponse> updatePostTitle(
            @RequestHeader Long userId,
            @PathVariable("post-id") Long postId,
            @RequestBody final PostTitleUpdateRequest postTitleUpdateRequest
    ) {
        return BaseResponse.success(SuccessMessage.OK, postService.updatePostTitle(userId, postId, postTitleUpdateRequest.title()));
    }

    @DeleteMapping("/{post-id}")
    public BaseResponse<?> deletePostById(
            @RequestHeader Long userId,
            @PathVariable("post-id") Long postId
    ) {
        postService.deletePostById(userId, postId);
        return BaseResponse.success(SuccessMessage.OK);
    }
}
