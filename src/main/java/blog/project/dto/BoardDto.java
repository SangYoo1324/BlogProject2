package blog.project.dto;

import blog.project.entity.Reply;
import blog.project.entity.Users;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDto {
    private Long board_id;

    private String title;


    private String content;


    private Long count;


    private Users user;


    private List<Reply> reply= new ArrayList<>();


    private String create_date;

    public BoardDto(Long board_id, String title, String content, Long count, Users user, List<Reply> reply, String create_date) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
        this.reply = reply;
        this.create_date = create_date;
    }
}
