package blog.project.repository;

import blog.project.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query(value = "SELECT * from reply WHERE board_id = :board_id",nativeQuery = true)
    List<Reply> findByBoardId(Long board_id);
}
