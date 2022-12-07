package blog.project.entity;

import blog.project.dto.BoardDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@DynamicInsert // null값이 되었을 때 쿼리문에서 아얘 제외되서 들어가게
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
    private Long count;

    @ManyToOne// board=many user=one
    @JoinColumn(name = "user_id")
    private Users user;

    @JsonIgnore
    //보드 객체를 생성 -> replyList생성->replyList에 reply마다 board객체 생성 무한루프(stackOverflow error) 잡아줌
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)// 연관관계의 주인이 아니다(foreign key 가 아니다)
    // Board가 삭제될 때 관련 Reply 임의로 삭제해줘야 오류 안남
    //즉, db에 컬럼 만들지 말고, board에서 참조해서 사용
    private List<Reply> reply= new ArrayList<>();


    @Column
    private String create_date;

    public Board(Long board_id, String title, String content, Long count, Users user, String create_date) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.count = count;
        this.user = user;
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
    public BoardDto toDto(){
        return new BoardDto(this.getBoard_id(),this.getTitle(), this.getContent(),this.getCount(),this.getUser(),this.getReply(),this.getCreate_date());
    }
}
