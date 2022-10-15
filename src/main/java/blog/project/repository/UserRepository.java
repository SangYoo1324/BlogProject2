package blog.project.repository;

import blog.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
//자동으로 bean 등록이 되기 때문에 생략 가능
public interface UserRepository  extends JpaRepository <User, Long> {
}

