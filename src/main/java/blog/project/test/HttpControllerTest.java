package blog.project.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

@GetMapping("/http/get")
    public String getTest(@RequestParam int id){
        return "get 요청"+id;
    }
    @GetMapping("/http/get/member")
    public String getTest(Member member){
    //builder 어노테이션으로 해당 엔티티 객체를 쉽게 생성 가능
        //(순서상관 없이 넣어도 됨)
    Member m = Member.builder().username("a").password("b").email("c").build();

        return "get 요청"+member.getId()+","+member.getUsername();
    }
@PostMapping("/http/post")
    public String postTest(){
        return "post 요청";
    }
@PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }
@DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
