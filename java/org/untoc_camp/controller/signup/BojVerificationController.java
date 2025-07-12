package org.untoc_camp.controller.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.signup.BojVerifyRequestDto;
import org.untoc_camp.service.signup.BojVerificationService;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class BojVerificationController {

    private final BojVerificationService bojVerificationService;

    @PostMapping("/boj/verify")
    public ResponseEntity<String> verify(@RequestBody BojVerifyRequestDto dto) {
        boolean result = bojVerificationService.verifyToken(dto.getBojId(), dto.getToken());
        if (result) {
            return ResponseEntity.ok("인증 성공");
        } else {
            return ResponseEntity.status(401).body("인증 실패 또는 토큰 만료");
        }
    }
}
