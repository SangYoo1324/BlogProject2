package blog.project.dto;

import blog.project.entity.Users;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserDto {
    private Long user_id;
    private String username;
    private String password;
    private String email;
//    private RoleType role;
//    private Timestamp create_Date;

    public UserDto(Long user_id, String username, String password, String email) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
//        this.role = role;
//        this.create_Date = create_Date;
    }


    public Users toEntiy() {
        return new Users(this.user_id,this.username,this.password,this.email, null);
    }
}
