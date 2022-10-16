package blog.project.service;

import blog.project.model.Board;
import blog.project.model.User;
import blog.project.repository.BoardRepository;
import blog.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//스프링이 컴포넌트 스캔을 통해 bean에 등록
@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Board write(Board board, String username){//title, content
      log.info(board.toString());
     User user= userRepository.findByUsername(username);
     board.setUser(user);

   Board saved= boardRepository.save(board);
    return saved;

    }


    public List<Board> allPosts() {
       return boardRepository.findAll();
    }
}
