package org.untoc_camp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.untoc_camp.entity.CurrentUserBojInfo;

import java.util.Optional;

@Repository
public interface CurrentBojInfoRepository extends JpaRepository<CurrentUserBojInfo, Long> {
    Optional<CurrentUserBojInfo> findByBojId(String bojId);
}
