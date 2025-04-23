package org.sopt.service;

import org.sopt.service.util.IdGenerator;
import org.sopt.domain.post.Post;
import org.sopt.domain.post.validator.PostValidator;
import org.sopt.repository.PostRepository;

import java.util.List;

// 비즈니스 로직 수행(Repository를 직접 사용)
public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private final IdGenerator idGenerator = new IdGenerator();

    public void createPost(String title) {
        Post post = new Post(idGenerator.generateId(), title);
        PostValidator.validateTitle(post.getTitle());
        postRepository.save(post);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id);
    }

    public boolean deletePostById(int id) {
        Post post = postRepository.findById(id);
        if (post != null) {
            postRepository.deletePost(post);
            return true;
        }
        return false;
    }

    public boolean updatePostTitle(int id, String newTitle) {
        PostValidator.validateTitle(newTitle);
        return postRepository.modifyPostTitleById(id, newTitle);
    }
}
