package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.User;
import kr.ac.mjc.blog.dto.UserDto;
import kr.ac.mjc.blog.dto.UserResponseDto;
import kr.ac.mjc.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDto join(UserDto userDto){
        UserResponseDto response=new UserResponseDto();
        Optional<User> result=userRepository.findById(userDto.getId());
        if(result.isEmpty()){   //같은 아이디로 가입된 사용자가 없는경우
            User user=new User();
            user.setId(userDto.getId());
            user.setPassword(userDto.getPassword());
            userRepository.save(user);
            response.setSuccess(true);
            response.setMessage("회원가입 완료");
        }
        else{      //같은 아이디로 가입된사용자가 있는경우
            response.setSuccess(false);
            response.setMessage("이미 가입된 아이디입니다");
        }
        return response;
    }

    public UserResponseDto login(UserDto userDto){
        UserResponseDto response=new UserResponseDto();
        Optional<User> result=userRepository.findOneByIdAndPassword(userDto.getId(), userDto.getPassword());
        if(result.isEmpty()){   //아이디,패스워드로 일치하는 사용자가 없음
            response.setSuccess(false);
            response.setMessage("아이디 또는 패스워드가 틀립니다");
        }
        else{   //일치하는 사용자 있음->로그인 성공
            response.setSuccess(true);
            response.setMessage("로그인완료");
            response.setUser(result.get());
                    
        }
        return response;
    }

    public User getUserInfo(String id){
        return userRepository.findById(id).get();
    }

}
