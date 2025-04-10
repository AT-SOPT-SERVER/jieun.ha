package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

// 요청을 받아 처리하는 역할
public class PostController {
    private final PostService postService = new PostService();
    private int postId;

    public void createPost(String title) {
        Post post = new Post(postId++, title);
        postService.createPost(post);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPost();
    }

    public Post getPostById(int postId) {
        return postService.getPostById(postId);
    }

    public boolean deletePostById(int postId) {
        return postService.deletePostById(postId);
    }

    public boolean updatePostTitle(int postId, String newTitle) {
        return postService.updatePostTitle(postId, newTitle);
    }
}
