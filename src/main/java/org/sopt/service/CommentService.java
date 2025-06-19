package org.sopt.service;

import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.CommentCreateRequest;
import org.sopt.dto.request.CommentUpdateRequest;
import org.sopt.dto.response.CommentResponse;
import org.sopt.dto.type.ErrorMessage;
import org.sopt.exception.CustomException;
import org.sopt.repository.CommentJpaRepository;
import org.sopt.repository.PostJpaRepository;
import org.sopt.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final CommentJpaRepository commentRepository;
    private final PostJpaRepository postRepository;
    private final UserJpaRepository userRepository;

    public CommentService(CommentJpaRepository commentRepository, PostJpaRepository postRepository, UserJpaRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createComment(Long userId, Long postId, CommentCreateRequest commentCreateRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessage.UNAUTHORIZED_ERROR));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorMessage.METHOD_ARGUMENT_ERROR));

        if (commentCreateRequest.content().length() > 300) {
            throw new CustomException(ErrorMessage.INVALID_COMMENT_ERROR);
        }

        Comment comment = CommentCreateRequest.toEntity(commentCreateRequest, user, post);
        commentRepository.save(comment);
    }

}
