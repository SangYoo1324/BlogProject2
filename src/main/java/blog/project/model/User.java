package blog.project.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
//@DynamicInsert// null인 값은 제외시키고 insert Query 발동
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    @Column(nullable = false,length =30)
    private String username;

    @Column(nullable = false,length =100)
    private String password;

    @Column(nullable = false,length =50)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;
@CreationTimestamp//시간 자동 입력
    private Timestamp create_Date;


    public User(Long userId, String username, String password, String email, Timestamp createDate) {
        this.userid = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_Date = createDate;
    }
    @Builder
    public User(String username, String password, String email, Timestamp createDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_Date = createDate;
    }

}
