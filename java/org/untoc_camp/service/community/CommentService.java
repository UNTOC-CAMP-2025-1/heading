package org.untoc_camp.service.community;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.untoc_camp.entity.community.Comment;
import org.untoc_camp.entity.community.Post;
import org.untoc_camp.repository.community.CommentRepository;
import org.untoc_camp.repository.community.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 작성
    public Comment addComment(Long postId, String author, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post);

        return commentRepository.save(comment);
    }

    // 댓글 목록 조회
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
