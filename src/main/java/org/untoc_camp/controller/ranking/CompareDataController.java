package org.untoc_camp.controller.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.untoc_camp.dto.ranking.ComparisonResultDto;
import org.untoc_camp.service.ranking.CompareDataService;
import org.untoc_camp.service.ranking.UserBojInfoRenewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ranking")
public class CompareDataController {

    private final CompareDataService compareDataService;
    private final UserBojInfoRenewService userBojInfoRenewService;

    @GetMapping
    public ComparisonResultDto getComparisonData() {
        return compareDataService.generateComparisonResults();
    }

    // ✅ 수동 갱신용 엔드포인트
    @GetMapping("/force-renew")
    public String forceRenewNow() {
        userBojInfoRenewService.updateAllUsersBojInfo();
        compareDataService.generateComparisonResults(); // 캐싱용
        return "🆙 수동 갱신 완료!";
    }
}
