package org.untoc_camp.dto.login;

// LoginRequestDto.java - 로그인 요청 데이터를 담는 객체
import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}