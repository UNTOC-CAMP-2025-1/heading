package org.untoc_camp.controller.community;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.community.PostDetailDto;
import org.untoc_camp.entity.community.Post;
import org.untoc_camp.repository.community.PostRepository;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class ReadController {

    private final PostRepository postRepository;

    @GetMapping("/read/{id}")
    public ResponseEntity<PostDetailDto> getPostDetail(@PathVariable Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        PostDetailDto dto = PostDetailDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .build();

        return ResponseEntity.ok(dto);
    }
}
