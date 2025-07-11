package org.untoc_camp.repository;

// UserRepository - User 엔티티와 DB를 연결하는 JPA 인터페이스
import org.springframework.data.jpa.repository.JpaRepository;
import org.untoc_camp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByBojId(String bojId); // 백준 ID 중복 확인
    boolean existsByNickname(String nickname);
    Optional<User> findByNickname(String nickname);
}


