package blog.project.entity.prac;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String fileName;
    @Column
    private String saveFileName;
    @Column
    private String filePath;
    @Column
    private String contentType;
    @Column
    private Long size;
    @Column
    private LocalDateTime registerDate;

}
