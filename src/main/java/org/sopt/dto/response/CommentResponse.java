package org.sopt.dto.response;

import org.sopt.domain.Comment;

public record CommentResponse(
        Long commentId,
        String content,
        String userId
) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUserName()
        );
    }
}
