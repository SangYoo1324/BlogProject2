package blog.project.repository;

import blog.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//@Repository
//자동으로 bean 등록이 되기 때문에 생략 가능
public interface UserRepository  extends JpaRepository <User, Long> {
    //JPA 네이밍 쿼리


    @Query(value ="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery = true)
    User findByUsernameAndPassword(String username, String password);
    @Query(value ="SELECT * FROM user WHERE username=?1",nativeQuery = true)
    User findByUsername(String username);
}

