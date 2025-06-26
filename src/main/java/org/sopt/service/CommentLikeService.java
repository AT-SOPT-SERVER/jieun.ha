package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Comment;
import org.sopt.domain.CommentLike;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeService {
    private final PostJpaRepository postRepository;
    private final CommentJpaRepository commentRepository;
    private final UserJpaRepository userRepository;
    private final CommentLikeJpaRepository commentLikeRepository;

    public CommentLikeService(PostJpaRepository postRepository, CommentJpaRepository commentRepository, UserJpaRepository userRepository, CommentLikeJpaRepository commentLikeJpaRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.commentLikeRepository = commentLikeJpaRepository;
    }

    @Transactional
    public void changeLike(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));

        Long postId = comment.getPost().getId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_FOUND_ERROR));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.UNAUTHORIZED_ERROR));

        Optional<CommentLike> existingLike = commentLikeRepository.findByCommentIdAndUserId(commentId, userId);

        if (existingLike.isPresent()) {
            commentLikeRepository.delete(existingLike.get());
        } else {
            commentLikeRepository.save(new CommentLike(comment, post, user));
        }
    }
}
