package org.sopt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentUpdateRequest(
        @NotBlank(message = "댓글 내용을 작성해 주세요.")
        @Size(max = 300, message = "댓글은 300자를 초과할 수 없습니다.")
        String content
) {
}
