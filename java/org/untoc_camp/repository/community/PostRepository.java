package org.untoc_camp.repository.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.untoc_camp.entity.community.Post;

// org.untoc_camp.repository.community.PostRepository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p.passwordHash FROM Post p WHERE p.id = :postId")
    String findPasswordById(@Param("postId") Long postId);
}
