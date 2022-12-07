package blog.project.repository;

import blog.project.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  BoardRepository  extends JpaRepository<Board, Long> {
    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query(value ="UPDATE Board b SET b.count= b.count+1 WHERE b.board_id =:board_id",nativeQuery = true)
    void updateView(Long board_id);
    }

