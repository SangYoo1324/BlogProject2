package blog.project.entity;

import blog.project.dto.ReplyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Slf4j
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
    private Users user;

    @CreationTimestamp
    private Timestamp create_date;

    public Reply(Long replyid, String content, Board board, Users user, Timestamp create_date) {
        this.reply_id = replyid;
        this.content = content;
        this.board = board;
        this.user = user;
        this.create_date = create_date;
    }

    public ReplyDto toDto() {
       return new ReplyDto(this.reply_id,this.content, this.board.getBoard_id(),this.user, this.create_date);
    }

    public Reply patch(ReplyDto patchDto, Long reply_id) {
        if(this.getReply_id() != reply_id){
            throw new IllegalArgumentException("업데이트 하려는 코멘트 아이디가 다릅니다");
        }
        if(this.getContent() != patchDto.getContent()){
            this.setContent(patchDto.getContent());
        }
        log.info("Patch 로그입니다: "+this.toString());
        return this;
    }
}
