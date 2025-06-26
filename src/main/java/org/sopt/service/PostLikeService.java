package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.domain.PostLike;
import org.sopt.domain.User;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.PostJpaRepository;
import org.sopt.repository.PostLikeJpaRepository;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeService {
    private final PostJpaRepository postRepository;
    private final UserJpaRepository userRepository;
    private final PostLikeJpaRepository postLikeJpaRepository;

    public PostLikeService(PostJpaRepository postRepository, UserJpaRepository userRepository, PostLikeJpaRepository postLikeJpaRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeJpaRepository = postLikeJpaRepository;
    }

    @Transactional
    public void changeLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.UNAUTHORIZED_ERROR));

        Optional<PostLike> existingLike = postLikeJpaRepository.findByPostIdAndUserId(postId, userId);

        if (existingLike.isPresent()) {
            postLikeJpaRepository.delete(existingLike.get());
        } else {
            postLikeJpaRepository.save(new PostLike(post, user));
        }
    }
}
