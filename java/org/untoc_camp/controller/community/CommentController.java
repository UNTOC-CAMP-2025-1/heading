package org.untoc_camp.controller.community;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.entity.community.Comment;
import org.untoc_camp.service.community.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public Comment addComment(@PathVariable Long postId, @RequestBody CommentRequest request) {
        return commentService.addComment(postId, request.getAuthor(), request.getContent());
    }

    // 댓글 목록 조회
    @GetMapping
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

    // DTO 클래스
    @Data
    public static class CommentRequest {
        private String author;
        private String content;
    }
}
