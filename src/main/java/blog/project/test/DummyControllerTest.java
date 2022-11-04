package blog.project.test;

import blog.project.entity.RoleType;
import blog.project.entity.Users;
import blog.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RestController
public class DummyControllerTest {
    @Autowired// 의존성 주입
    private UserRepository userRepository;

    //POST
    // http://localhost:5000/dummy/join
    @PostMapping("dummy/join")
            public String join(@RequestBody Users user){
    log.info("join 정보: 아이디: "+user.getUsername()+" 패스워드: "+user.getPassword());
    user.setRole(RoleType.valueOf("USER"));
    userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }

    //Get from ID
    //http://localhost:5000/dummy/user/1
    @GetMapping("/dummy/user/{id}")
    public Users Detail(@PathVariable Long id){
   Users user =  userRepository.findById(id).orElseThrow(()->new
           IllegalStateException(" id "+id+"에 해당 유저는 없습니다:  "));
   return user;
    }

    //Get Whole LISt
    //http://localhost:5000/dummy/user
    @GetMapping("dummy/users")
    public List<Users> list(){
        return userRepository.findAll();
    }

    //한 페이지당 1건의 데이터를 리턴받게
    //http://localhost:5000/dummy/user?page=0
    @GetMapping("dummy/user")
    public List<Users> pageList(@PageableDefault(size=1, sort = "user_id", direction =
    Sort.Direction.ASC)Pageable pageable){
        return userRepository.findAll(pageable).getContent();
    }

    //업데이트
    //http://localhost:5000/dummy/user/1
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users requestUser){
        //@RequestBody 로 Json data를 요청 => java Message converter가 Json 을 자바 오브젝트로 바꿔줌
        log.info("id:"+id);
        log.info("pw:"+requestUser.getPassword());
        log.info("email:"+requestUser.getEmail());

        Users user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        userRepository.save(user);

        return null;
    }
    //Delete
    //http://localhost:5000/dummy/user/1
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id){
        try{
            userRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 대상 id 가 없습니다";
        }

        return "삭제되었습니다";
    }
}
