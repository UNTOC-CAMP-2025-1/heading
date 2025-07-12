package org.untoc_camp.service.ranking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.untoc_camp.entity.CurrentUserBojInfo;
import org.untoc_camp.repository.CurrentBojInfoRepository;
import org.untoc_camp.util.SolvedAcClient;
import org.untoc_camp.dto.ranking.SolvedAcUserStatsDto;

@Service
@RequiredArgsConstructor
public class CurrentUserBojInfoService {

    private final CurrentBojInfoRepository currentBojInfoRepository;
    private final SolvedAcClient solvedAcClient;

    public void updateCurrentInfo(String bojId, String nickname) {
        SolvedAcUserStatsDto info = solvedAcClient.fetchInfo(bojId)
                .orElseThrow(() -> new IllegalStateException("유저 정보 조회 실패"));

        currentBojInfoRepository.findByBojId(bojId).ifPresentOrElse(existing -> {
            // 이미 존재하면 갱신
            existing.setNickname(nickname);
            existing.setRating(info.getRating());
            existing.setTier(info.getTier());
            existing.setSolvedCount(info.getSolvedCount());
            existing.setRank(info.getRank());
            currentBojInfoRepository.save(existing);
        }, () -> {
            // 없으면 새로 저장
            CurrentUserBojInfo newInfo = new CurrentUserBojInfo();
            newInfo.setBojId(bojId);
            newInfo.setNickname(nickname);
            newInfo.setRating(info.getRating());
            newInfo.setTier(info.getTier());
            newInfo.setSolvedCount(info.getSolvedCount());
            newInfo.setRank(info.getRank());
            currentBojInfoRepository.save(newInfo);
        });
    }
}
