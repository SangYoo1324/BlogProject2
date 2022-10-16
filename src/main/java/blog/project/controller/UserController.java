package blog.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    public static boolean isLoggedIn= false;
    public static String username;

    @GetMapping("/user/joinForm")
    public String joinForm(){
        return "joinForm";
    }
    @GetMapping("/user/loginForm")
    public String loginForm(){

        return "loginForm";
    }
}
