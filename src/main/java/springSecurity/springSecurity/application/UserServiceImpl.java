package springSecurity.springSecurity.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springSecurity.springSecurity.application.dto.DefaultResponseDto;
import springSecurity.springSecurity.application.dto.LoginDto;
import springSecurity.springSecurity.application.dto.UserDto;
import springSecurity.springSecurity.domain.UserEntity;
import springSecurity.springSecurity.domain.repository.UserInfoRepository;
import springSecurity.springSecurity.view.servlet.JwtTokenProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserInfoRepository userInfoRepository;

    @Override
    public UserEntity joinUser(UserDto userDto) {
        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .userName(userDto.getUserName())
                .nickName(userDto.getNickName())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        userInfoRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        UserEntity userEntity = userInfoRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 이름입니다."));
        if(!passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())){
            throw new RuntimeException("잘못된 비번임");
        }
        return jwtTokenProvider.createToken(userEntity.getEmail(), userEntity.getRoles());
    }

    @Override
    public List<UserEntity> list() {
        return userInfoRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUser(String email) {
        return userInfoRepository.findByEmail(email);
    }
}
