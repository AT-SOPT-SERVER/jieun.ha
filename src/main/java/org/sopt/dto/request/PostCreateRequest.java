package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.sopt.domain.Post;
import org.sopt.domain.User;

public record PostCreateRequest(
        @NotBlank(message = "제목은 필수입니다.") String title,
        @NotBlank(message = "내용은 필수입니다.") String content
) {
    public Post toPostEntity(User user) {
        return new Post(title, content, user);
    }
}