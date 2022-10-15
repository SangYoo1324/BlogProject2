package blog.project.test;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;



@Builder
    public Member(String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }




}
