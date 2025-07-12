package org.untoc_camp.controller.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.untoc_camp.dto.ranking.ComparisonResultDto;
import org.untoc_camp.dto.ranking.ComparisonUserDto;
import org.untoc_camp.entity.CurrentUserBojInfo;
import org.untoc_camp.service.ranking.RankingDataService;
import org.untoc_camp.service.ranking.UserBojInfoRenewService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking")
public class CompareDataController {

    private final RankingDataService rankingDataService;
    private final UserBojInfoRenewService userBojInfoRenewService;

    @GetMapping("/main")
    public ComparisonResultDto getTop3Comparison() {
        ComparisonResultDto full = rankingDataService.generateComparisonResults();

        List<ComparisonUserDto> dayTop3 = full.getDayData().stream().limit(3).collect(Collectors.toList());
        List<ComparisonUserDto> weekTop3 = full.getWeekData().stream().limit(3).collect(Collectors.toList());
        List<CurrentUserBojInfo> nowTop3 = full.getNowData().stream().limit(3).collect(Collectors.toList());

        return new ComparisonResultDto(dayTop3, weekTop3, nowTop3);
    }

    @GetMapping("/ranking")
    public ComparisonResultDto getAllComparisonData() {
        return rankingDataService.generateComparisonResults();
    }

    @GetMapping("/force-renew")
    public String forceRenewNow() {
        userBojInfoRenewService.updateAllUsersBojInfo();
        rankingDataService.generateComparisonResults();
        return "🆙 수동 갱신 완료!";
    }
}
