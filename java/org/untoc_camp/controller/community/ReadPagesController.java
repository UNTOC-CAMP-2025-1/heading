package org.untoc_camp.controller.community;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.community.PostSummaryDto;
import org.untoc_camp.entity.community.Post;
import org.untoc_camp.repository.community.PostRepository;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class ReadPagesController {

    private final PostRepository postRepository;

    @GetMapping("/read/pages")
    public ResponseEntity<Page<PostSummaryDto>> getPostList(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Post> postPage = postRepository.findAll(pageable);

        Page<PostSummaryDto> resultPage = postPage.map(post -> PostSummaryDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .commentCount((long) post.getComments().size())
                .build());

        return ResponseEntity.ok(resultPage);
    }
}
