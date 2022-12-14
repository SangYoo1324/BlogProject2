package blog.project.entity;

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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false,length =30)
    private String username;

    @Column(nullable = false,length =100)
    private String password;

    @Column(nullable = false,length =50)
    private String email;

    @Column
   @Enumerated(EnumType.STRING)
    private RoleType roles;
@CreationTimestamp//시간 자동 입력
    private Timestamp create_date;


    public Users(Long user_id, String username, String password, String email, Timestamp create_date) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_date = create_date;
    }
    @Builder
    public Users(String username, String password, String email, Timestamp create_date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_date = create_date;
    }


}
