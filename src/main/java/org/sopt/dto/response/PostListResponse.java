package org.sopt.dto.response;

import java.util.List;

public record PostListResponse(
        List<PostSummary> posts
) {
    public record PostSummary(
            String postTitle,
            String userName
    ) {
    }
}