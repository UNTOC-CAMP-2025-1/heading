

package org.untoc_camp.controller.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.register.RegisterRequestDto;
import org.untoc_camp.service.register.RegisterService;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto dto) {
        return registerService.register(dto);
    }
}