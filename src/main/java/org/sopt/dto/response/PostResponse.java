package org.sopt.dto.response;

public record PostResponse(
        String title,
        String content,
        String userName
) {
}
