package blog.project.api;

import blog.project.controller.UserController;
import blog.project.dto.ResponseDto;
import blog.project.dto.UserDto;
import blog.project.entity.RoleType;
import blog.project.entity.User;
import blog.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static blog.project.controller.UserController.isLoggedIn;

@Slf4j
@RestController
public class UserApiController {
    @Autowired
    private UserService userService;



    @PostMapping("/api/user")
public ResponseEntity<User> save(@RequestBody UserDto userDto){
        //실제로 DB에 insert를 하고 return

      User result=  userService.signup(userDto);
        log.info("userAPIController:  회원가입 정보가 db에 저장되었습니다");
        return ResponseEntity.status(HttpStatus.OK).body(result);
}

@PostMapping("/api/user/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto){
        log.info("userAPIController:  Login 호출됨");
        isLoggedIn= true;
        User principal = userService.login(userDto);
        return (principal != null)?ResponseEntity.status(HttpStatus.OK).body(principal):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

}

//Logout 기능
@PostMapping("/api/user/logout")
public String login(@RequestBody String sign){
           isLoggedIn= false;
    return  null;

}
}
