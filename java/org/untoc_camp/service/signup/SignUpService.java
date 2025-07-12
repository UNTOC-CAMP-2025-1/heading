package org.untoc_camp.service.signup;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.untoc_camp.dto.ranking.SolvedAcUserStatsDto;
import org.untoc_camp.dto.signup.SignUpRequestDto;
import org.untoc_camp.entity.User;
import org.untoc_camp.entity.UserBojInfo;
import org.untoc_camp.entity.CurrentUserBojInfo;
import org.untoc_camp.repository.UserRepository;
import org.untoc_camp.service.ranking.CurrentUserBojInfoService;
import org.untoc_camp.service.ranking.UserBojInfoInitializerService;
import org.untoc_camp.util.SolvedAcClient;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final CurrentUserBojInfoService currentUserBojInfoService;
    private final UserBojInfoInitializerService userBojInfoInitializerService;

    public ResponseEntity<?> register(SignUpRequestDto dto) {
        if (userRepository.existsByBojId(dto.getBojId())) {
            return ResponseEntity.badRequest().body("중복된 백준 ID");
        }

        // 1. User 저장
        User user = new User();
        user.setBojId(dto.getBojId());
        user.setNickname(dto.getNickname());
        user.setStatusMessage(dto.getStatusMessage());
        user.setGoalTier(dto.getGoalTier());
        user.setEmail(dto.getEmail());
        user.setGrade(dto.getGrade());
        user.setMajor(dto.getMajor());
        userRepository.save(user);

        // 2. 주간 데이터 저장
        userBojInfoInitializerService.initializePastWeek(dto.getBojId(), dto.getNickname());

        // 3. current 데이터 저장
        currentUserBojInfoService.updateCurrentInfo(dto.getBojId(), dto.getNickname());

        return ResponseEntity.ok("회원가입 완료");
    }
}
