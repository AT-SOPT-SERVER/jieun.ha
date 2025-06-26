package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.dto.base.BaseResponse;
import org.sopt.dto.request.CommentCreateRequest;
import org.sopt.dto.request.CommentUpdateRequest;
import org.sopt.dto.response.CommentResponse;
import org.sopt.dto.type.SuccessMessage;
import org.sopt.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/board/{postId}/comments") // "board/{postId}에 속한 comments" 라고 명시
public class CommentController {
    private final CommentService commentService;

    protected CommentController(CommentService commentService) { this.commentService = commentService; }

    @PostMapping()
    public BaseResponse<?> createComment(
            @RequestHeader Long userId,
            @PathVariable("postId") Long postId,
            @Valid @RequestBody final CommentCreateRequest commentCreateRequest
    ) {
        commentService.createComment(userId, postId, commentCreateRequest);
        return BaseResponse.success(SuccessMessage.CREATED);
    }

    @PatchMapping("/{commentId}")
    public BaseResponse<CommentResponse> updateComment(
            @RequestHeader Long userId,
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId,
            @RequestBody final CommentUpdateRequest commentUpdateRequest
    ) {
        return BaseResponse.success(SuccessMessage.OK, commentService.updateComment(commentId, userId, commentUpdateRequest));
    }

    @DeleteMapping("/{commentId}")
    public BaseResponse<?> deleteCommentById(
            @RequestHeader Long userId,
            @PathVariable("postId") Long postId,
            @PathVariable("commentId") Long commentId
    ) {
        commentService.deleteComment(userId, commentId);
        return BaseResponse.success(SuccessMessage.OK);
    }
}