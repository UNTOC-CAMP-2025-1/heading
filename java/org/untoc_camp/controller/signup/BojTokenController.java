package org.untoc_camp.controller.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.signup.BojVerifyRequestDto;
import org.untoc_camp.service.signup.BojVerificationService;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class BojTokenController {
    private final BojVerificationService bojVerificationService;

    @PostMapping("/boj/token")
    public ResponseEntity<String> createToken(@RequestBody BojVerifyRequestDto bojVerifyRequestDto) {
        String token = bojVerificationService.generateAndSaveToken(bojVerifyRequestDto.getBojId());
        return ResponseEntity.ok(token);
    }

}

