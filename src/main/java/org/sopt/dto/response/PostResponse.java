package org.sopt.dto.response;

import org.sopt.domain.Post;

import java.util.List;

public record PostResponse(
        String title,
        String content,
        String userName,
        List<CommentResponse> comments
) {
    public static PostResponse from(Post post) {
        List<CommentResponse> commentResponseList = post.getComments().stream()
                .map(CommentResponse::from)
                .toList();

        return new PostResponse(post.getTitle(), post.getContent(), post.getUser().getName(), commentResponseList);
    }
}