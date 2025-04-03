package org.untoc_camp.dto.register;

// RegisterRequestDto.java - 회원가입 요청 데이터를 담는 객체
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    private String bojId;
}
