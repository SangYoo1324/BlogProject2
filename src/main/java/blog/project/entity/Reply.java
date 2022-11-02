package blog.project.entity;

import blog.project.dto.ReplyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reply_id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne// 여러개의 reply 는 한개의 board에 실린다
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name="user_id") // user객체를 참조하지만 sql에서는 primary key
    //즉 id만 땡겨옴
    private User user;

    @CreationTimestamp
    private Timestamp create_date;

    public Reply(Long replyid, String content, Board board, User user, Timestamp create_date) {
        this.reply_id = replyid;
        this.content = content;
        this.board = board;
        this.user = user;
        this.create_date = create_date;
    }

    public ReplyDto toDto() {
       return new ReplyDto(this.reply_id,this.content, this.board.getBoard_id(),this.user.getUser_id(), this.create_date);
    }
}
