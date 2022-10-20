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
    public Board post(Long boardid){
        Board post = boardRepository.findById(boardid).orElseThrow
                (()->new IllegalArgumentException("해당 게시글을 찾을 수 없습니다")
        );
        return post;
    }

    public Board delete(Long boardid) {
        Board target = boardRepository.findById(boardid).orElse(null);
        boardRepository.delete(target);
        return target;
    }

    @Transactional
    public Board edit(Long boardid, String username, Board edited) {
        //db에 url boardid 값의 id가 존재하지 않는 경우 null
     Board originalPost =   boardRepository.findById(boardid).orElseThrow(()->{
           new IllegalArgumentException("게시글이 존재하지 않습니다");
            return null;
        });
     if(boardid != originalPost.getBoardid()){
         log.info("수정하려는 게시글 id와 db게시글id가 일치하지 않습니다");
         return null;
     }


        originalPost.patch(edited);
        Board patched = boardRepository.save(originalPost);

                log.info(patched.toString());
        return patched;
    }
}
