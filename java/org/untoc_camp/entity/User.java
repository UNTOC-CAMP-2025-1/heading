package org.untoc_camp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_user") // "user"는 예약어라 피함
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bojId", unique = true, nullable = false)
    private String bojId;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    private String statusMessage;

    private String goalTier;

    @Column(nullable = false)
    private String email;

    private String grade;

    private String major;
}
