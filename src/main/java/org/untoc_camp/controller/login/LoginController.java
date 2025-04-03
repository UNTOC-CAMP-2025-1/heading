package org.untoc_camp.controller.login;

// LoginController.java - 로그인 요청을 처리하는 컨트롤러
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.login.LoginRequestDto;
import org.untoc_camp.service.login.LoginService;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return loginService.login(dto.getUsername(), dto.getPassword());
    }
}
