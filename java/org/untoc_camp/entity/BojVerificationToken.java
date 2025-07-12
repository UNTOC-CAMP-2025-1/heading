package org.untoc_camp.entity;

// BojVerificationToken.java - 백준 인증을 위한 임시 토큰 저장 엔티티
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class BojVerificationToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bojId;
    private String token;
    private LocalDateTime createdAt;

    // 생성된 지 3분이 지나면 만료됨
    public boolean isExpired() {
        return createdAt.plusMinutes(3).isBefore(LocalDateTime.now());
    }
}
