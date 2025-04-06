package org.untoc_camp.service.ranking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.untoc_camp.dto.ranking.SolvedAcUserStatsDto;
import org.untoc_camp.entity.User;
import org.untoc_camp.entity.UserBojInfo;
import org.untoc_camp.repository.UserRepository;
import org.untoc_camp.repository.UserBojInfoRepository;
import org.untoc_camp.util.SolvedAcClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBojInfoRenewService {

    private final UserRepository userRepository;
    private final UserBojInfoRepository userBojInfoRepository;
    private final SolvedAcClient solvedAcClient;

    public void updateAllUsersBojInfo() {
        List<User> users = userRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        String dayOfWeek = now.getDayOfWeek().toString();
        int hour = resolveToThreeHour(now.getHour());

        for (User user : users) {
            String handle = user.getBojId();

            solvedAcClient.fetchInfo(handle).ifPresentOrElse(
                    stats -> {
                        UserBojInfo bojInfo = userBojInfoRepository
                                .findByBojIdAndDayOfWeekAndTime(handle, dayOfWeek, hour)
                                .orElseGet(UserBojInfo::new);

                        bojInfo.setBojId(handle);
                        bojInfo.setRating(stats.getRating());
                        bojInfo.setTier(stats.getTier());
                        bojInfo.setRank(stats.getRank());
                        bojInfo.setSolvedCount(stats.getSolvedCount());
                        bojInfo.setDayOfWeek(dayOfWeek);
                        bojInfo.setTime(hour);
                        bojInfo.setCreatedAt(now);

                        userBojInfoRepository.save(bojInfo);
                        log.info("✅ {} 정보 갱신 완료 ({} {}시)", handle, dayOfWeek, hour);
                    },
                    () -> log.warn("❌ {} 정보 갱신 실패 - solved.ac 응답 없음", handle)
            );
        }
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
