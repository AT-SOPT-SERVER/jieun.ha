package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.sopt.domain.Comment;
import org.sopt.domain.Post;
import org.sopt.domain.User;

public record CommentCreateRequest(
        @NotBlank(message = "댓글 내용을 작성해 주세요.")
        @Size(max = 300, message = "댓글은 300자를 초과할 수 없습니다.")
        String content
) {
    public static Comment toEntity(CommentCreateRequest request, User user, Post post) {
        return new Comment(request.content(), user, post);
    }
}
