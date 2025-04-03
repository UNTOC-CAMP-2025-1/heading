package org.untoc_camp.controller.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.register.UsernameCheckDto;
import org.untoc_camp.service.register.RegisterService;

@RestController
@RequestMapping("/check-username")
@RequiredArgsConstructor
public class UsernameCheckController {
    private final RegisterService registerService;

    // localhost:8080/check-username?username=username
    @PostMapping
    public ResponseEntity<Boolean> checkUsername(@RequestBody UsernameCheckDto usernameCheckDto) {
        return ResponseEntity.ok(registerService.isUsernameAvailable(usernameCheckDto.getUsername()));
    }
}
