package org.untoc_camp.controller.community;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.community.DeleteDto;
import org.untoc_camp.repository.community.PostRepository;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class DeleteController {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/delete")
    public ResponseEntity<String> deletePost(@RequestBody DeleteDto deleteDto) {
        Long postId = deleteDto.getPostId();
        String rawPassword = deleteDto.getPassword();

        // DB에서 암호화된 비밀번호만 가져옴
        String encryptedPassword = postRepository.findPasswordById(postId);
        if (encryptedPassword == null) {
            return ResponseEntity.status(404).body("게시글이 존재하지 않습니다.");
        }

        // 암호 비교
        if (!passwordEncoder.matches(rawPassword, encryptedPassword)) {
            return ResponseEntity.status(401).body("비밀번호가 일치하지 않습니다.");
        }

        // 삭제
        postRepository.deleteById(postId);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }
}
