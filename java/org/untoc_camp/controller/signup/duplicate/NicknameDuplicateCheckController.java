package org.untoc_camp.controller.signup.duplicate;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.service.duplicate.CheckDuplicateService;

@RestController
@RequestMapping("/signup/check/duplicate")
@RequiredArgsConstructor
public class NicknameDuplicateCheckController {
    private final CheckDuplicateService checkDuplicateService;

    @PostMapping("/nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestBody NicknameRequest request) {
        return ResponseEntity.ok(checkDuplicateService.isDuplicateNickname(request.getNickname()));
    }

    @Data
    public static class NicknameRequest {
        private String nickname;
    }
}
