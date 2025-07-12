package org.untoc_camp.controller.community;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.community.PostDto;
import org.untoc_camp.entity.community.Post;
import org.untoc_camp.repository.community.PostRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        post.setCreatedAt(LocalDateTime.now());

        post.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        postRepository.save(post);
        return ResponseEntity.ok("작성 완료");
    }
}
