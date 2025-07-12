package org.untoc_camp.dto.signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String bojId;            // 백준 아이디
    private String nickname;         // 사용할 닉네임
    private String statusMessage;    // 상태 메시지
    private String goalTier;         // 목표 티어
    private String email;            // 이메일
    private String grade;            // 학년 (ex: "1", "2", ...)
    private String major;            // 전공 (학과)
}
