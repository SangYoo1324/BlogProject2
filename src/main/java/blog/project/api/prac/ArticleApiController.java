package blog.project.api.prac;

import blog.project.entity.prac.Article;
import blog.project.repository.prac.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/article/post")
    public ResponseEntity<Article> setArticle(@RequestBody Article article){
        article.setRegisterDate(LocalDateTime.now());
      log.info(article.toString());
      log.info("article 포스트 완료");
              articleRepository.save(article);
      return ResponseEntity.status(HttpStatus.OK).body(article);
    }

}
