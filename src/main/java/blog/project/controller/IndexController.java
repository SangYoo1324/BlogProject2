package blog.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("")
    public @ResponseBody String index(){
        //mustache 기본 폴더 src/main/resources/
        //viewResolver 설정: templates(prefix), .mustache (suffix) 생략가능
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/login")
    public @ResponseBody String login(){
        return "loginForm";
    }

    @GetMapping("/join")
    public @ResponseBody String join(){
        return "join";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){
        //responsebody 어노테이션: 자바 객체를 HttpResponse의 본문
        //responseBody의 내용으로 매핑
        return "Join complete";
    }
}
