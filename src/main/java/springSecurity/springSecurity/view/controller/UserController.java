package springSecurity.springSecurity.view.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import springSecurity.springSecurity.application.UserService;
import springSecurity.springSecurity.application.dto.DefaultResponseDto;
import springSecurity.springSecurity.application.dto.LoginDto;
import springSecurity.springSecurity.application.dto.UserDto;
import springSecurity.springSecurity.domain.UserEntity;

import javax.validation.Valid;


@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody UserDto userDto){
        DefaultResponseDto result = DefaultResponseDto.builder()
                .message("회원가입")
                .data(userService.joinUser(userDto))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginDto loginDto){
        String token =  userService.loginUser(loginDto);

        DefaultResponseDto result = DefaultResponseDto.builder()
                .message("토큰")
                .data(token)
                .build();

        return new ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("/user/list")
    public ResponseEntity list(){
        DefaultResponseDto result = DefaultResponseDto.builder()
                .message("리스트")
                .data(userService.list())
                .build();

       return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity getUser(@RequestParam String email){
        DefaultResponseDto result = DefaultResponseDto.builder()
                .message("유저정보")
                .data(userService.getUser(email))
                .build();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
