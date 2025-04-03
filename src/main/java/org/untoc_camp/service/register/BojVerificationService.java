// BojVerificationService - 백준 bio를 통한 인증 로직 처리

package org.untoc_camp.service.register;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.untoc_camp.entity.BojVerificationToken;
import org.untoc_camp.repository.BojVerificationTokenRepository;
import org.untoc_camp.util.SolvedAcClient;

import java.util.Optional;
import java.util.UUID;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BojVerificationService {
    private final BojVerificationTokenRepository tokenRepo;
    private final SolvedAcClient solvedAcClient;

    // 랜덤 인증 메시지 생성 및 저장
    public String generateAndSaveToken(String bojId) {
        String token = UUID.randomUUID().toString().substring(0, 8);
        BojVerificationToken entity = new BojVerificationToken();
        entity.setBojId(bojId);
        entity.setToken(token);
        entity.setCreatedAt(LocalDateTime.now());
        tokenRepo.save(entity);
        return token;
    }

    // solved.ac에서 bio 조회 후 토큰 포함 여부 확인
    public boolean verifyToken(String bojId, String token) {
        Optional<BojVerificationToken> optional = tokenRepo.findByBojId(bojId);
        if (optional.isEmpty()) return false;

        BojVerificationToken entity = optional.get();
        if (entity.isExpired()) {
            tokenRepo.delete(entity);
            return false;
        }

        String bio = solvedAcClient.getBioByHandle(bojId);
        if (bio != null && bio.equals(token)) {
            tokenRepo.delete(entity);
            return true;
        }
        return false;
    }
}