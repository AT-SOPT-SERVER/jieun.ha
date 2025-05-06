package org.sopt.dto.response;

import org.sopt.domain.post.Post;

import java.util.List;

public record PostListResponse(List<Post> postList) {
}
