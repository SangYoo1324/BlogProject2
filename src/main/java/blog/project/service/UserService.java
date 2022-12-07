package blog.project.service;

import blog.project.dto.UserDto;
import blog.project.entity.RoleType;
import blog.project.entity.Users;
import blog.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//스프링이 컴포넌트 스캔을 통해 bean에 등록
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Users signup(UserDto userDto){
        Users user = userDto.toEntiy();
        user.setRoles(RoleType.USER);//RoleType은 자동 생성이 안되서 일단 강제로 넣어줌
        try{
             userRepository.save(user);
             return user;
        }catch (Exception e){
            e.printStackTrace();
            log.info("회원가입 절차에 뭔가가 잘못됬다");

        }return null;

    }
@Transactional(readOnly = true)
    public Users login(UserDto userDto) {
        log.info(userDto.toString());
        Users userInput = userDto.toEntiy();
       Users dbIdPw = userRepository.findByUsernameAndPassword(userInput.getUsername(),userInput.getPassword());
        if(dbIdPw!=null)
        log.info("아이디: "+dbIdPw.getUsername()+"패스워드"+dbIdPw.getPassword());
    return dbIdPw ;
    }
}
