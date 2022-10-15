package blog.project.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BlogControllerTest {
    @GetMapping("/test/hello")
public String  hello(){
    return "hello world";
}
}
