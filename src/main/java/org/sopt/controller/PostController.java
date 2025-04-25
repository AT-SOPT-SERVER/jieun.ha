package org.sopt.controller;

import org.sopt.domain.post.Post;
import org.sopt.dto.PostRequest;
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

    @PostMapping("/post")
    public void createPost(@RequestBody final PostRequest postRequest) {
        postService.createPost(postRequest.title());
    }

    @GetMapping("/get")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/get/{post-id}")
    public ResponseEntity<Post> getPostById(@PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @DeleteMapping("/delete/{post-id}")
    public ResponseEntity<Boolean> deletePostById(@PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(postService.deletePostById(postId));
    }

    @PatchMapping("/patch/{post-id}")
    public ResponseEntity<Boolean> updatePostTitle(@PathVariable("post-id") Long postId, @RequestBody final PostRequest postRequest) {
        return ResponseEntity.ok(postService.updatePostTitle(postId, postRequest.title()));
    }
}
