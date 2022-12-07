package blog.project.entity.prac;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime registerDate;

    public Article(Long id, String title, String content, LocalDateTime registerDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registerDate = registerDate;
    }
}
