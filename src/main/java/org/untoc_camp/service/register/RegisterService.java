package org.untoc_camp.service.register;

// RegisterService - 회원가입 및 중복 확인 비즈니스 로직
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.untoc_camp.dto.register.RegisterRequestDto;
import org.untoc_camp.entity.User;
import org.untoc_camp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(RegisterRequestDto dto) {
        // 아이디 중복 확인
        if (userRepository.existsByUsername(dto.getUsername())) {
            return ResponseEntity.badRequest().body("중복된 아이디");
        }
        // 백준 ID 중복 확인
        if (userRepository.existsByBojId(dto.getBojId())) {
            return ResponseEntity.badRequest().body("중복된 백준 ID");
        }

        // 유저 생성 및 저장
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setBojId(dto.getBojId());
        userRepository.save(user);
        return ResponseEntity.ok("회원가입 완료");
    }

    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public boolean isBojIdAvailable(String bojId) {
        return !userRepository.existsByBojId(bojId);
    }
}
