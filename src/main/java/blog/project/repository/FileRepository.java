package blog.project.repository;

import blog.project.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Modifying
    @Query(value = "SELECT * FROM File Where board_id=?1",nativeQuery = true)
    List<File> findByBoardId(Long board_id);
}
