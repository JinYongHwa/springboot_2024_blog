package kr.ac.mjc.blog.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog.dto.UserDto;
import kr.ac.mjc.blog.dto.UserResponseDto;
import kr.ac.mjc.blog.repository.UserRepository;
import kr.ac.mjc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/join")
    public ResponseEntity<UserResponseDto> join(@RequestBody UserDto userDto){
        UserResponseDto response= userService.join(userDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserDto userDto, HttpSession session){
        UserResponseDto response= userService.login(userDto);
        if(response.isSuccess()){   //로그인 성공시 세션 생성
            session.setAttribute("loginUserId",response.getUser().getId());
        }
        return ResponseEntity.ok(response);
    }

}
