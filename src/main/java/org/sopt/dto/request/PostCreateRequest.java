package org.sopt.dto.request;

import org.sopt.domain.Post;
import org.sopt.domain.User;

public record PostCreateRequest(
        String title,
        String content
) {
    public Post toPostEntity(User user) {
        return new Post(title, content, user);
    }
}