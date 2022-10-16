package blog.project.service;

import blog.project.model.User;
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
    public int signup(User user){
        try{
             userRepository.save(user);
             return 1;
        }catch (Exception e){
            e.printStackTrace();
            log.info("회원가입 절차에 뭔가가 잘못됬다");

        }return -1;

    }
@Transactional(readOnly = true)
    public User login(User user) {
       User dbIdPw = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        log.info("아이디: "+dbIdPw.getUsername()+"패스워드"+dbIdPw.getPassword());
    return dbIdPw ;
    }
}
