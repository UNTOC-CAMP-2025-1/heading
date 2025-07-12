package org.untoc_camp.repository.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.untoc_camp.entity.community.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);
}
