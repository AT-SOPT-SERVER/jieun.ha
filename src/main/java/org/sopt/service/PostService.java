package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.PostCreateRequest;
import org.sopt.dto.response.PostListResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostJpaRepository;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostJpaRepository postRepository;
    private final UserJpaRepository userRepository;

    protected PostService(final PostJpaRepository postRepository, UserJpaRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createPost(Long userId, PostCreateRequest postCreateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.UNAUTHORIZED_ERROR));
        Post post = postCreateRequest.toPostEntity(user);
        postRepository.save(post);
    }

    public PostListResponse getAllPost(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }
        List<Post> postList = postRepository.findAll();
        List<PostListResponse.PostSummary> postSummaries = postList.stream()
                .map( post -> new PostListResponse.PostSummary(
                        post.getTitle(),
                        post.getUser().getName()
                ))
                .toList();
        return new PostListResponse(postSummaries);
    }

    public PostResponse getPostById(Long userId, Long postId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }

        return postRepository.findById(postId)
                .map( post -> new PostResponse(
                        post.getTitle(),
                        post.getContent(),
                        post.getUser().getName()
                ))
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));
    }

    public PostResponse updatePostTitle(Long userId, Long postId, String newTitle) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));

        if (!post.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }

        post.renameTitle(newTitle);
        postRepository.save(post);
        return new PostResponse(
                post.getTitle(),
                post.getContent(),
                post.getUser().getName()
        );
    }

    public boolean deletePostById(Long userId, Long postId) {
        if (!userRepository.existsById(userId)) {
            throw new CustomException(ErrorMessage.UNAUTHORIZED_ERROR);
        }
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));
        postRepository.delete(post);
        return true;

    }
}
