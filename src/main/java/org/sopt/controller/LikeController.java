package org.sopt.controller;

import org.sopt.dto.base.BaseResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.CommentLikeService;
import org.sopt.service.PostLikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final PostLikeService postLikeService;
    private final CommentLikeService commentLikeService;

    public LikeController(PostLikeService postLikeService, CommentLikeService commentLikeService) {
        this.postLikeService = postLikeService;
        this.commentLikeService = commentLikeService;
    }

    @PatchMapping("/post/{post-id}")
    public BaseResponse<?> changePostLike(
            @RequestHeader Long userId,
            @PathVariable("post-id") Long postId
    ) {
        postLikeService.changeLike(postId, userId);
        return BaseResponse.success(SuccessMessage.OK);
    }

    @PatchMapping("/comment/{comment-id}")
    public BaseResponse<?> changeCommentLike(
            @RequestHeader Long userId,
            @PathVariable("comment-id") Long commentId
    ) {
        commentLikeService.changeLike(commentId, userId);
        return BaseResponse.success(SuccessMessage.OK);
    }
}
