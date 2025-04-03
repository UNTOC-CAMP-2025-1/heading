// 백준 아이디 중복 체크 컨트롤러

package org.untoc_camp.controller.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.register.BojCheckDto;
import org.untoc_camp.service.register.RegisterService;

@RestController
@RequestMapping("/check-boj-id")
@RequiredArgsConstructor
public class BojCheckController {
    private final RegisterService registerService;

    // localhost:8080/check-boj-id?bojId=bojId
    @PostMapping
    public ResponseEntity<Boolean> checkBojId(@RequestBody BojCheckDto bojCheckDto) {
        return ResponseEntity.ok(registerService.isBojIdAvailable(bojCheckDto.getBojId()));
    }
}
