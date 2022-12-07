package blog.project.repository;

import blog.project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
//자동으로 bean 등록이 되기 때문에 생략 가능
public interface UserRepository  extends JpaRepository <Users, Long> {
    //JPA 네이밍 쿼리


    @Query(value ="SELECT * FROM users WHERE username=?1 AND password=?2",nativeQuery = true)
    Users findByUsernameAndPassword(String username, String password);
    @Query(value ="SELECT * FROM users WHERE username=?1",nativeQuery = true)
    Users findByUsername(String username);
}

