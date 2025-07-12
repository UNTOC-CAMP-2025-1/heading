package org.untoc_camp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "current_user_boj_info")
@Data
@NoArgsConstructor
public class CurrentUserBojInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "boj_id")
    private String bojId;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "tier")
    private int tier;

    @Column(name = "ranking")
    private int rank;

    @Column(name = "solved_count")
    private int solvedCount;

    @Column(name = "day_of_week")
    private String dayOfWeek;

    @Column(name = "time")
    private int time;  // 또는 LocalTime

    @Column(name = "created_at")
    private LocalDateTime createdAt;  // Optional
}
