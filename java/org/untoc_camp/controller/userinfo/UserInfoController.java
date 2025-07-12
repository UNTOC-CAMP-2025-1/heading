package org.untoc_camp.controller.userinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.untoc_camp.dto.ranking.ComparisonResultDto;
import org.untoc_camp.dto.ranking.ComparisonUserDto;
import org.untoc_camp.dto.userinfo.UserInfoDto;
import org.untoc_camp.entity.User;
import org.untoc_camp.repository.UserRepository;
import org.untoc_camp.service.ranking.RankingDataService;
import org.untoc_camp.service.userinfo.UserInfoService;

import java.util.Map;

@RestController
@RequestMapping("/userinfo")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final UserRepository userRepository;
    private final RankingDataService rankingDataService;


    @GetMapping("/{nickname}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String nickname) {
        UserInfoDto userInfo = userInfoService.getUserInfoByNickname(nickname);
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/{nickname}/ranking")
    public ResponseEntity<Map<String, ComparisonUserDto>> getUserInfoRanking(@PathVariable String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 닉네임입니다: " + nickname));

        Map<String, ComparisonUserDto> comparison = rankingDataService.generateIndividualComparisonResult(user.getId());
        return ResponseEntity.ok(comparison);
    }
}
