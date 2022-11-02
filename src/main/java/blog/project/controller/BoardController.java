package blog.project.controller;

import blog.project.dto.ReplyDto;
import blog.project.entity.Board;
import blog.project.entity.User;
import blog.project.repository.ReplyRepository;
import blog.project.repository.UserRepository;
import blog.project.service.BoardService;
import blog.project.service.ReplyService;
import ch.qos.logback.core.joran.spi.ElementSelector;
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
    @Autowired
    private ReplyService replyService ;
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
        if(isLoggedIn== true)
        model.addAttribute("user",loggedinUser );



    return "index";
}

@GetMapping("/board/saveForm/{username}")
    public String saveForm(Model model, @PathVariable String username){

    if(isLoggedIn== true)
        model.addAttribute("user", userRepository.findByUsername(username));

        return "board/saveForm";
}

@GetMapping("/board/postList/{username}")
    public String postList(Model model,@PathVariable String username){
        if(isLoggedIn== true){
            model.addAttribute("user", userRepository.findByUsername(username));
            List<Board> posts=  boardService.allPosts();
            model.addAttribute("board", posts);
            return "board/postList";
        }
    else{
            log.info("로그인 없이 주소이동했습니다");
            return "redirect:/board/main";
        }


}

@GetMapping("board/postList/show/{username}/{board_id}")
    public String showPost(Model model,@PathVariable String username, @PathVariable Long board_id){
    if(isLoggedIn== true){
        model.addAttribute("user", userRepository.findByUsername(username));
        Board target =  boardService.post(board_id);
        model.addAttribute("target", target);
        List<ReplyDto> replies = replyService.replyList(board_id);
        model.addAttribute("Reply", replies);
        return "board/showPost";
    }
  else{
      log.info("로그인 없이 주소이동했습니다");
        return "redirect:/board/main";
    }


}


}
