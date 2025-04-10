package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

// 비즈니스 로직 수행(Repository를 직접 사용)
public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id);
    }

    public boolean deletePostById(int id) {
        return postRepository.deletePostById(id);
    }

    public boolean updatePostTitle(int id, String newTitle) {
        return postRepository.modifyPostTitleById(id, newTitle);
    }
}
