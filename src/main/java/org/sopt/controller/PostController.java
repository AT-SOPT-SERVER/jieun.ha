package org.sopt.controller;

import org.sopt.domain.post.Post;
import org.sopt.dto.base.BaseResponse;
import org.sopt.dto.request.PostRequest;
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
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.title());
    }

    @GetMapping()
    public BaseResponse<List<Post>> getAllPosts() {
        return BaseResponse.success(postService.getAllPost());
    }

    @GetMapping("/{post-id}")
    public BaseResponse<Post> getPostById(@PathVariable("post-id") Long postId) {
        return BaseResponse.success(postService.getPostById(postId));
    }

    @DeleteMapping("/{post-id}")
    public BaseResponse<Boolean> deletePostById(@PathVariable("post-id") Long postId) {
        return BaseResponse.success(postService.deletePostById(postId));
    }

    @PatchMapping("/{post-id}")
    public BaseResponse<Boolean> updatePostTitle(@PathVariable("post-id") Long postId, @RequestBody final PostRequest postRequest) {
        return BaseResponse.success(postService.updatePostTitle(postId, postRequest.title()));
    }
}
