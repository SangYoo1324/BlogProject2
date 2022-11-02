package blog.project.dto;

import blog.project.entity.Board;
import blog.project.entity.Reply;
import blog.project.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReplyDto {
    private Long reply_id;//auto
    private String content;
    private Long board_id;//ManyToOne join column:board_id
    private Long user_id;//ManyToOne join column: user_id
    private Timestamp create_date; //auto

    public ReplyDto(Long reply_id, String content, Long board_id, Long user_id, Timestamp create_date) {
        this.reply_id = reply_id;
        this.content = content;
        this.board_id =  board_id;
        this.user_id = user_id;
        this.create_date = create_date;
    }

    public Reply toEntity( User user, Board board) {
        return new Reply(null, this.content,board, user, null);

    }
}
