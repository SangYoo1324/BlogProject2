package blog.project.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardid;

    @Column(nullable = false,length =100)
    private String title;

    @Lob // 섬머노트 라이브러리로 html태그가 섞여서 디자인되서 대용량 데이터 필요
    @Column
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne// board=many user=one
    @JoinColumn(name = "user_Id")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)// 연관관계의 주인이 아니다(foreign key 가 아니다)
    //즉, db에 컬럼 만들지 말고, board에서 참조해서 사용
    private List<Reply> reply;
    @CreationTimestamp
    private Timestamp createDate;

    public Board(int board_Id, String title, String content, int count, User user,List<Reply> reply,Timestamp createDate) {
        this.boardid = board_Id;
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
        this.reply = reply;
        this.createDate = createDate;
    }
@Builder
    public Board( String title, String content, int count, User user, List<Reply>reply,Timestamp createDate) {
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
        this.reply = reply;
        this.createDate = createDate;
    }
}
