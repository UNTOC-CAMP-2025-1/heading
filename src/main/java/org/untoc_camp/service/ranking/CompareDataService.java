package org.untoc_camp.service.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.untoc_camp.dto.ranking.ComparisonResultDto;
import org.untoc_camp.dto.ranking.ComparisonUserDto;
import org.untoc_camp.entity.User;
import org.untoc_camp.entity.UserBojInfo;
import org.untoc_camp.repository.UserRepository;
import org.untoc_camp.repository.UserBojInfoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompareDataService {

    private final UserRepository userRepository;
    private final UserBojInfoRepository userBojInfoRepository;

    public ComparisonResultDto generateComparisonResults() {
        List<User> users = userRepository.findAll();

        LocalDateTime now = LocalDateTime.now();
        String todayDayOfWeek = now.getDayOfWeek().toString(); // 오늘 요일
        int hour = now.getHour();
        int currentTime = resolveToThreeHour(hour); // 3시간 단위 정규화

        int midnightTime = 0; // 00시

        // 🔁 6일 전 날짜 기준으로 weekly 비교 수행
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

        return new ComparisonResultDto(dayData, weekData);
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
