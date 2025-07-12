package org.untoc_camp.service.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.untoc_camp.dto.ranking.ComparisonResultDto;
import org.untoc_camp.dto.ranking.ComparisonUserDto;
import org.untoc_camp.entity.CurrentUserBojInfo;
import org.untoc_camp.entity.User;
import org.untoc_camp.entity.UserBojInfo;
import org.untoc_camp.repository.CurrentBojInfoRepository;
import org.untoc_camp.repository.UserRepository;
import org.untoc_camp.repository.UserBojInfoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingDataService {

    private final UserRepository userRepository;
    private final UserBojInfoRepository userBojInfoRepository;
    private final CurrentBojInfoRepository currentBojInfoRepository;

    public ComparisonResultDto generateComparisonResults() {
        List<User> users = userRepository.findAll();

        LocalDateTime now = LocalDateTime.now();
        String todayDayOfWeek = now.getDayOfWeek().toString();
        int hour = now.getHour();
        int currentTime = resolveToThreeHour(hour);
        int midnightTime = 0;

        LocalDate sixDaysAgo = LocalDate.now().minusDays(6);
        String sixDaysAgoDayOfWeek = sixDaysAgo.getDayOfWeek().toString();

        List<ComparisonUserDto> dayData = new ArrayList<>();
        List<ComparisonUserDto> weekData = new ArrayList<>();

        for (User user : users) {
            String bojId = user.getBojId();

            Optional<UserBojInfo> currentOpt = userBojInfoRepository
                    .findByBojIdAndDayOfWeekAndTime(bojId, todayDayOfWeek, currentTime);

            Optional<UserBojInfo> dayBaseOpt = userBojInfoRepository
                    .findByBojIdAndDayOfWeekAndTime(bojId, todayDayOfWeek, midnightTime);

            Optional<UserBojInfo> weekBaseOpt = userBojInfoRepository
                    .findByBojIdAndDayOfWeekAndTime(bojId, sixDaysAgoDayOfWeek, midnightTime);

            if (currentOpt.isPresent() && dayBaseOpt.isPresent()) {
                dayData.add(ComparisonUserDto.of(user, currentOpt.get(), dayBaseOpt.get()));
            }
            if (currentOpt.isPresent() && weekBaseOpt.isPresent()) {
                weekData.add(ComparisonUserDto.of(user, currentOpt.get(), weekBaseOpt.get()));
            }
        }

        // 정렬
        dayData.sort(Comparator.comparingLong(ComparisonUserDto::getRating).reversed());
        weekData.sort(Comparator.comparingLong(ComparisonUserDto::getRating).reversed());

        // 현재 데이터 정렬 추가
        List<CurrentUserBojInfo> nowData = currentBojInfoRepository.findAll().stream()
                .sorted(Comparator.comparingLong(CurrentUserBojInfo::getRating).reversed())
                .collect(Collectors.toList());

        return new ComparisonResultDto(dayData, weekData, nowData);
    }

    // 특정 유저 1명에 대한 비교 결과만 반환하는 메서드
    public Map<String, ComparisonUserDto> generateIndividualComparisonResult(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return Collections.emptyMap();

        User user = userOpt.get();
        String bojId = user.getBojId();

        LocalDateTime now = LocalDateTime.now();
        String todayDayOfWeek = now.getDayOfWeek().toString();
        int hour = now.getHour();
        int currentTime = resolveToThreeHour(hour);
        int midnightTime = 0;

        LocalDate sixDaysAgo = LocalDate.now().minusDays(6);
        String sixDaysAgoDayOfWeek = sixDaysAgo.getDayOfWeek().toString();

        Optional<UserBojInfo> currentOpt = userBojInfoRepository
                .findByBojIdAndDayOfWeekAndTime(bojId, todayDayOfWeek, currentTime);

        Optional<UserBojInfo> dayBaseOpt = userBojInfoRepository
                .findByBojIdAndDayOfWeekAndTime(bojId, todayDayOfWeek, midnightTime);

        Optional<UserBojInfo> weekBaseOpt = userBojInfoRepository
                .findByBojIdAndDayOfWeekAndTime(bojId, sixDaysAgoDayOfWeek, midnightTime);

        Map<String, ComparisonUserDto> result = new HashMap<>();

        // day 비교
        if (currentOpt.isPresent() && dayBaseOpt.isPresent()) {
            result.put("day", ComparisonUserDto.of(user, currentOpt.get(), dayBaseOpt.get()));
        }

        // week 비교
        if (currentOpt.isPresent() && weekBaseOpt.isPresent()) {
            result.put("week", ComparisonUserDto.of(user, currentOpt.get(), weekBaseOpt.get()));
        }

        // total = 현재 기준 정보만 포함 (비교 없음)
        if (currentOpt.isPresent()) {
            result.put("total", ComparisonUserDto.of(user, currentOpt.get(), null));
        }

        return result;
    }


    private int resolveToThreeHour(int hour) {
        if (hour < 6) return 0;
        if (hour < 9) return 6;
        if (hour < 12) return 9;
        if (hour < 15) return 12;
        if (hour < 18) return 15;
        if (hour < 21) return 18;
        return 21;
    }
}
