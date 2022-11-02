package blog.project.service;

import blog.project.entity.Board;
import blog.project.entity.User;
import blog.project.repository.BoardRepository;
import blog.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Board post(Long board_id){
        Board post = boardRepository.findById(board_id).orElseThrow
                (()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다")
        );
        return post;
    }

    public Board delete(Long board_id) {
        Board target = boardRepository.findById(board_id).orElse(null);
        boardRepository.delete(target);
        return target;
    }

    @Transactional
    public Board edit(Long board_id, String username, Board edited) {
        //db에 url board_id 값의 id가 존재하지 않는 경우 null
     Board originalPost =   boardRepository.findById(board_id).orElseThrow(()->{
           new IllegalArgumentException("게시글이 존재하지 않습니다");
            return null;
        });
     if(board_id != originalPost.getBoard_id()){
         log.info("수정하려는 게시글 id와 db게시글id가 일치하지 않습니다");
         return null;
     }


        originalPost.patch(edited);
        Board patched = boardRepository.save(originalPost);

                log.info(patched.toString());
        return patched;
    }
}
