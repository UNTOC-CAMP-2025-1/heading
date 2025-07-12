package org.untoc_camp.service.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.untoc_camp.entity.UserBojInfo;
import org.untoc_camp.repository.UserBojInfoRepository;
import org.untoc_camp.util.SolvedAcClient;
import org.untoc_camp.dto.ranking.SolvedAcUserStatsDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBojInfoInitializerService {

    private final UserBojInfoRepository userBojInfoRepository;
    private final SolvedAcClient solvedAcClient;

    public void initializePastWeek(String bojId, String nickname) {
        SolvedAcUserStatsDto info = solvedAcClient.fetchInfo(bojId)
                .orElseThrow(() -> new IllegalStateException("유저 정보 조회 실패"));

        LocalDateTime now = LocalDateTime.now();
        List<UserBojInfo> records = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate day = now.minusDays(i).toLocalDate();
            String dayOfWeek = day.getDayOfWeek().toString();

            for (int hour : List.of(0, 6, 9, 12, 15, 18, 21)) {
                UserBojInfo stat = new UserBojInfo();
                stat.setBojId(bojId);
                stat.setNickname(nickname);
                stat.setRating(info.getRating());
                stat.setTier(info.getTier());
                stat.setRank(info.getRank());
                stat.setSolvedCount(info.getSolvedCount());
                stat.setDayOfWeek(dayOfWeek);
                stat.setTime(hour);
                stat.setCreatedAt(day.atTime(hour, 0));
                records.add(stat);
            }
        }
        userBojInfoRepository.saveAll(records);
    }
}