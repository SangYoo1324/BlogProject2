package blog.project.entity;

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
    private Long board_id;

    @Column(nullable = false,length =100)
    private String title;

    @Lob // 섬머노트 라이브러리로 html태그가 섞여서 디자인되서 대용량 데이터 필요
    @Column
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne// board=many user=one
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)// 연관관계의 주인이 아니다(foreign key 가 아니다)
    //즉, db에 컬럼 만들지 말고, board에서 참조해서 사용
    private List<Reply> reply;
    @CreationTimestamp
    private Timestamp create_date;

    public Board(Long board_id, String title, String content, int count, User user,List<Reply> reply,Timestamp create_date) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
        this.reply = reply;
        this.create_date = create_date;
    }
@Builder
    public Board( String title, String content, int count, User user, List<Reply>reply,Timestamp create_date) {
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
        this.reply = reply;
        this.create_date = create_date;
    }


    public void patch(Board edited) {
        if(edited.title != null){
            this.title = edited.title;
        }
        if(edited.content != null){
            this.content = edited.content;
        }
    }
}
