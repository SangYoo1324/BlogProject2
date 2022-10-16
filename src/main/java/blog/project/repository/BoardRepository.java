package blog.project.repository;

import blog.project.model.Board;
import blog.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


    public interface  BoardRepository  extends JpaRepository<Board, Long> {
    }

