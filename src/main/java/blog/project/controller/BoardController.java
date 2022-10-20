package blog.project.controller;

import blog.project.model.Board;
import blog.project.model.User;
import blog.project.repository.UserRepository;
import blog.project.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static blog.project.controller.UserController.isLoggedIn;

@Slf4j
@Controller
public class BoardController {
    @Autowired
   private UserRepository userRepository;
    @Autowired
    private BoardService boardService;
    //메인화면
    @GetMapping("/board/main")
    public String index(Model model) {


        return "index";
    }
    //로그인 됬을때 메인 화면
    @GetMapping("/board/main/{username}")
    public String index(Model model,@PathVariable String username) {
        User loggedinUser = userRepository.findByUsername(username);
        log.info("메인화면으로 이동하였습니다"+loggedinUser.getUsername());
        model.addAttribute("user",loggedinUser );

//        if(isLoggedIn==true) {
//            log.info("isLoggedIn 은 True입니다");
//            model.addAttribute("loginStatus", "loggin");
//
//        }else{
//            log.info("isLoggedIn 은 false입니다");
//        }
    return "index";
}

@GetMapping("/board/saveForm/{username}")
    public String saveForm(Model model, @PathVariable String username){


        model.addAttribute("user", userRepository.findByUsername(username));

        return "board/saveForm";
}

@GetMapping("/board/postList/{username}")
    public String postList(Model model,@PathVariable String username){
    model.addAttribute("user", userRepository.findByUsername(username));
    List<Board> posts=  boardService.allPosts();
    model.addAttribute("board", posts);
return "board/postList";
}

@GetMapping("board/postList/show/{username}/{boardid}")
    public String showPost(Model model,@PathVariable String username, @PathVariable Long boardid){
    model.addAttribute("user", userRepository.findByUsername(username));
   Board target =  boardService.post(boardid);
    model.addAttribute("target", target);
        return "board/showPost";
}

//temp
//    @GetMapping("/board/postList/{username}")
//    public String postListTemp(){
//
//    }
}
