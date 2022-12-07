package blog.project.controller.prac;

import blog.project.entity.prac.Article;
import blog.project.repository.prac.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @GetMapping("/index")
    public String index(){
        return "prac/index";
    }

    @GetMapping("/{id}")
    public String getArticle(Model model, @PathVariable Long id){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",article);
        return "prac/detail";
    }

    @GetMapping("/list")
    public String getArticleList(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);
        log.info("list진입");
        return "prac/list";
    }

}
