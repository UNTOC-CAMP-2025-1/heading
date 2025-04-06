package org.untoc_camp.entity;

// User.java - 사용자 정보를 저장하는 엔티티. 회원가입 시 사용됨.
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user") // 예약어 "user" 대신 안전한 이름으로 변경
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 증가
    private Long id;

    @Column(unique = true) // 중복 방지
    private String username;

    private String password;

    @Column(name = "boj_id", unique = true)
    private String bojId; // 백준 아이디
}
