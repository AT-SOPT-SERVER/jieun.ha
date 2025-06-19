package org.sopt.dto.response;

import org.sopt.domain.Post;

import java.util.List;

public record PostListResponse(
        List<PostSummary> posts
) {
    public record PostSummary(
            String postTitle,
            String userName
    ) {
    }

    public static PostListResponse from(List<Post> postList) {
        List<PostSummary> summaries = postList.stream()
                .map(post -> new PostSummary(
                        post.getTitle(),
                        post.getUser().getName()
                ))
                .toList();

        return new PostListResponse(summaries);
    }
}