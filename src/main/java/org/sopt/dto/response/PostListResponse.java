package org.sopt.dto.response;

import org.sopt.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public record PostListResponse(
        List<PostSummary> posts,
        int currentPage,
        int totalPages
) {
    public record PostSummary(
            String postTitle,
            String userName
    ) {}

    public static PostListResponse from(Page<Post> postPage) {
        List<PostSummary> summaries = postPage.getContent().stream()
                .map(post -> new PostSummary(
                        post.getTitle(),
                        post.getUser().getName()
                ))
                .toList();

        return new PostListResponse(
                summaries,
                postPage.getNumber() + 1,  // 0-based index → 1-based
                postPage.getTotalPages()
        );
    }
}
