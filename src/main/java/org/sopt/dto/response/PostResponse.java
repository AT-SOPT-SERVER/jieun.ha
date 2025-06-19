package org.sopt.dto.response;

import org.sopt.domain.Post;

public record PostResponse(
        String title,
        String content,
        String userName
) {
    public static PostResponse of(Post post) {
        return new PostResponse(post.getTitle(), post.getContent(), post.getUser().getName());
    }
}