package org.untoc_camp.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.untoc_camp.service.ranking.UserBojInfoRenewService;
import org.untoc_camp.service.ranking.CompareDataService;

@Component
@RequiredArgsConstructor
public class UserInfoScheduler {

    private final UserBojInfoRenewService userBojInfoRenewService;
    private final CompareDataService compareDataService;

    @Scheduled(cron = "0 0 0,6,9,12,15,18,21 * * *") // 매일 0시, 6시, 9시, 12시, 15시, 18시, 21시에 실행됨
    public void updateAndCompareUserBojInfo() {
        userBojInfoRenewService.updateAllUsersBojInfo();
        compareDataService.generateComparisonResults(); // 캐싱용
    }

}
