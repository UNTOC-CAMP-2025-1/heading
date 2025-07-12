package org.untoc_camp.dto.signup;

// BojVerifyRequestDto.java - 백준 인증 요청 시 필요한 데이터를 담는 객체
import lombok.Data;

@Data
public class BojVerifyRequestDto {
    private String bojId;
    private String token;
}