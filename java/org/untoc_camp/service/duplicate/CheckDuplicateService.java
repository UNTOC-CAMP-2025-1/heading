package org.untoc_camp.service.duplicate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.untoc_camp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CheckDuplicateService {
    private final UserRepository userRepository;

    public Boolean isDuplicateNickname(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    public boolean isDuplicateBojId(String bojId) {
        return !userRepository.existsByBojId(bojId);
    }
}
