package blog.project.repository.prac;

import blog.project.entity.prac.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
