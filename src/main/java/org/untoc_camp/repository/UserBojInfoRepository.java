package org.untoc_camp.repository;

import org.untoc_camp.entity.UserBojInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserBojInfoRepository extends JpaRepository<UserBojInfo, Long> {

    // ✅ 1. 1주일 전 동일 요일+시간대의 기록 찾기 (갱신용)
    Optional<UserBojInfo> findByBojIdAndDayOfWeekAndTimeAndCreatedAtBetween(
            String bojId, String dayOfWeek, int time, LocalDateTime start, LocalDateTime end);

    // ✅ 2. 신규 회원 가입 시, 과거 7일치 동일 요일/시간대 채우기
    List<UserBojInfo> findAllByBojIdAndDayOfWeekAndTime(String bojId, String dayOfWeek, int time);

    Optional<UserBojInfo> findByBojIdAndDayOfWeekAndTime(String bojId, String dayOfWeek, int time);
}