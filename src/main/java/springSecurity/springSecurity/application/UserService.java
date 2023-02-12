package springSecurity.springSecurity.application;

import org.apache.catalina.User;
import springSecurity.springSecurity.application.dto.DefaultResponseDto;
import springSecurity.springSecurity.application.dto.LoginDto;
import springSecurity.springSecurity.application.dto.UserDto;
import springSecurity.springSecurity.domain.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public UserEntity joinUser(UserDto userDto);

    public String loginUser(LoginDto loginDto);

    public List<UserEntity> list();

    public Optional<UserEntity> getUser(String email);
}
