package blog.project.api;

import blog.project.dto.ResponseDto;
import blog.project.model.RoleType;
import blog.project.model.User;
import blog.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
public ResponseDto<Integer> save(@RequestBody User user){
        //실제로 DB에 insert를 하고 return
        user.setRole(RoleType.USER);//RoleType은 자동 생성이 안되서 일단 강제로 넣어줌
      int result=  userService.signup(user);
        log.info("회원가입 정보가 db에 저장되었습니다");
        return new ResponseDto<Integer>(HttpStatus.OK, result);
}
}
