package org.untoc_camp.controller.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.signup.SignUpRequestDto;
import org.untoc_camp.service.ranking.UserBojInfoInitializerService;
import org.untoc_camp.service.signup.SignUpService;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService SignUpService;
    private final UserBojInfoInitializerService userBojInfoInitializerService;

    @PostMapping("/finish")
    public ResponseEntity<?> register(@RequestBody SignUpRequestDto signUpRequestDto) {
        return SignUpService.register(signUpRequestDto);
    }
}