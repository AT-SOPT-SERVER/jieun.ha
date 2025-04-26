package org.sopt.controller;

import org.sopt.domain.post.Post;
import org.sopt.dto.base.BaseResponse;
import org.sopt.dto.request.PostRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// 요청을 받아 처리하는 역할
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/board/post")
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.title());
    }

    @GetMapping("/board")
    public BaseResponse<List<Post>> getAllPosts() {
        return BaseResponse.success(postService.getAllPost());
    }

    @GetMapping("/board/{post-id}")
    public BaseResponse<Post> getPostById(@PathVariable("post-id") Long postId) {
        return BaseResponse.success(postService.getPostById(postId));
    }

    @DeleteMapping("/board/{post-id}/delete")
    public BaseResponse<Boolean> deletePostById(@PathVariable("post-id") Long postId) {
        return BaseResponse.success(postService.deletePostById(postId));
    }

    @PatchMapping("/board/{post-id}/modify")
    public BaseResponse<Boolean> updatePostTitle(@PathVariable("post-id") Long postId, @RequestBody final PostRequest postRequest) {
        return BaseResponse.success(postService.updatePostTitle(postId, postRequest.title()));
    }
}
