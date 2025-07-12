package org.untoc_camp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.untoc_camp.entity.BojVerificationToken;

import java.util.Optional;

// BojVerificationTokenRepository - 인증 토큰을 저장하고 불러오는 레포지토리
public interface BojVerificationTokenRepository extends JpaRepository<BojVerificationToken, Long> {
    Optional<BojVerificationToken> findByBojId(String bojId);
    void deleteAllByBojId(String bojId);
}
