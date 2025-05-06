package org.sopt.service;

import org.sopt.domain.post.Post;
import org.sopt.domain.post.validator.PostValidator;
import org.sopt.repository.PostRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 비즈니스 로직 수행(Repository를 직접 사용)
public class PostService {
    private final PostRepositoryImpl postRepository;

    protected PostService(final PostRepositoryImpl postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(String title) {
        PostValidator.validateTitle(title);
        Post post = new Post(title);
        postRepository.save(post);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public boolean deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return true;

    }

    public boolean updatePostTitle(Long id, String newTitle) {
        PostValidator.validateTitle(newTitle);
        Post post = postRepository.findById(id).orElseThrow();
        post.renameTitle(newTitle);
        postRepository.save(post);
        return true;
    }
}
