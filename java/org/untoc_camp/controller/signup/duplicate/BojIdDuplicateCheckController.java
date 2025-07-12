package org.untoc_camp.controller.signup.duplicate;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.service.duplicate.CheckDuplicateService;

@RestController
@RequestMapping("/signup/check/duplicate")
@RequiredArgsConstructor
public class BojIdDuplicateCheckController {
    private final CheckDuplicateService checkDuplicateService;

    @PostMapping("/bojId")
    public ResponseEntity<Boolean> checkBojId(@RequestBody BojIdRequest request) {
        return ResponseEntity.ok(checkDuplicateService.isDuplicateBojId(request.getBojId()));
    }

    @Data
    public static class BojIdRequest {
        private String bojId;
    }
}
