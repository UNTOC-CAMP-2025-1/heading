package org.untoc_camp.service.userinfo;

import lombok.RequiredArgsConstructor;
import org.untoc_camp.dto.userinfo.UserInfoDto;
import org.untoc_camp.entity.UserBojInfo;
import org.untoc_camp.repository.UserBojInfoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserBojInfoRepository userBojInfoRepository;

    public UserInfoDto getUserInfoByNickname(String nickname) {
        UserBojInfo userBojInfo = userBojInfoRepository.findFirstByNicknameOrderByCreatedAtDesc(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해당 닉네임의 유저가 존재하지 않습니다."));

        UserInfoDto dto = new UserInfoDto();
        dto.setName(nickname);
        dto.setBojId(userBojInfo.getBojId());
        dto.setTier(convertTierToString(userBojInfo.getTier())); // <- 변환 적용
        dto.setTotalSolved(userBojInfo.getSolvedCount());

        return dto;
    }

    private String convertTierToString(int tier) {
        if (tier == 0) return "Unrated";

        String[] levels = {"Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"};
        int group = (tier - 1) / 5;
        int level = 5 - (tier - 1) % 5;

        if (group >= 0 && group < levels.length) {
            return levels[group] + " " + level;
        } else {
            return "Unknown";
        }
    }
}
