package blog.project.model;

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
    private Long replyid;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne// 여러개의 reply 는 한개의 board에 실린다
    @JoinColumn(name="boardid")
    private Board board;

    @ManyToOne
    @JoinColumn(name="userid") // user객체를 참조하지만 sql에서는 primary key
    //즉 id만 땡겨옴
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    public Reply(Long replyid, String content, Board board, User user, Timestamp createDate) {
        this.replyid = replyid;
        this.content = content;
        this.board = board;
        this.user = user;
        this.createDate = createDate;
    }
}
