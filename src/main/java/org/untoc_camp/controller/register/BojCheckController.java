// 백준 아이디 중복 체크 컨트롤러

package org.untoc_camp.controller.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.service.register.RegisterService;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class BojCheckController {
    private final RegisterService registerService;

    // localhost:8080/check-boj-id?bojId=bojId
    @PostMapping("/check_bojId")
    public ResponseEntity<Boolean> checkBojId(@RequestBody String bojId) {
        return ResponseEntity.ok(registerService.isBojIdAvailable(bojId));
    }
}
