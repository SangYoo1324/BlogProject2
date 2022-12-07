package blog.project.service;

import blog.project.dto.BoardDto;
import blog.project.entity.Board;
import blog.project.entity.Reply;
import blog.project.entity.Users;
import blog.project.repository.BoardRepository;
import blog.project.repository.FileRepository;
import blog.project.repository.ReplyRepository;
import blog.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;


//스프링이 컴포넌트 스캔을 통해 bean에 등록
@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplyService replyService;

    @Autowired
    private FileService fileService;

    @Transactional
    public Board write(Board board, String username){//title, content
      log.info(board.toString());
     Users user= userRepository.findByUsername(username);
     //user 엔티티 주입
     board.setUser(user);
     //timeStamp 주입
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        board.setCreate_date(timestamp.toString().substring(0,10));

    fileService.injectBoardId(board);// 파일업로드가 됬다면 파일에 boardId 주입

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
        //게시글에 존재하는 reply들 삭제(reply가 board에서 FK(Board_id)를 갖다쓰기 때문에 reply삭제가 안되면
        //Referential integrity constraint violation 에러가 난다 )
        target.getReply().stream().forEach(s->replyService.deleteReply(board_id,s.getUser().getUser_id(),s.getReply_id()));
        // mappedBy 된 replyList 클리어(sql db에는 반영되지 않았기 때문에 Spring에서 삭제해줘야함)
        target.getReply().clear();

        fileService.deleteFile(board_id);// 보드에 파일이 있다면 지움
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

    //MainPage 인기 게시글
    public List<BoardDto> topFourPosts() {
        List<Board> boardList = boardRepository.findAll();

//        조회수에 따라 정렬
        for(int i =0; i<boardList.size()-1;i++){
            for(int j = i+1; j<boardList.size(); j++){
                Long c1 = boardList.get(i).getCount();
                Long c2 = boardList.get(j).getCount();
                if(c1<c2){
                    Board imshi = boardList.get(i);
                    boardList.set(i,boardList.get(j));
                    boardList.set(j,imshi);
                }
            }
        }

        ListIterator<Board> boardListIt= boardList.listIterator();
        while(boardListIt.hasNext()){// 4개 이하남을때까지 boardList 삭제
            System.out.println(boardListIt.nextIndex()+"/ "+boardListIt.next());
        }
        //끝에서부터 게시글 4개만 남기고 자름
        for(int i = boardList.size()-1; i>=4; i--){  //뒤에서부터 index 4(5번째) 까지 지움
            boardList.remove(i);
        }

        System.out.println("shibal"+boardList.size());
        //dto로 바꿔서 반환
       return boardList.stream().map(s->s.toDto()).collect(Collectors.toList());
    }
}
