package org.untoc_camp.controller.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.register.BojVerifyRequestDto;
import org.untoc_camp.service.register.BojVerificationService;

import java.util.Map;

@RestController
@RequestMapping("/boj/token")
@RequiredArgsConstructor
public class BojTokenController {
    private final BojVerificationService bojVerificationService;

    @PostMapping
    public ResponseEntity<String> createToken(@RequestBody BojVerifyRequestDto bojVerifyRequestDto) {
        String token = bojVerificationService.generateAndSaveToken(bojVerifyRequestDto.getBojId());
        return ResponseEntity.ok(token);
    }

}

