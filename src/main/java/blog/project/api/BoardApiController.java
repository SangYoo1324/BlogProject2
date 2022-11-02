package blog.project.api;

import blog.project.dto.ResponseDto;
import blog.project.entity.Board;
import blog.project.service.BoardService;
import blog.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BoardApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
//Get은 boardController에서 바로
//Post
    @PostMapping("/api/board/savePost/{username}")
    public ResponseDto<Integer> savePost(@RequestBody Board board, @PathVariable String username){
        //실제로 DB에 insert를 하고 return
        boardService.write(board, username);

        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }

    //Delete Post
    @DeleteMapping("/api/board/deletePost/{username}/{board_id}")
    public ResponseEntity<Board> deletePost(@PathVariable Long board_id){
    Board deletedBoard=boardService.delete(board_id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedBoard);
    }

    //Edit Post
    @PatchMapping("/api/board/editPost/{username}/{board_id}")
    public ResponseEntity<Board> deletePost(@PathVariable Long board_id, @PathVariable String username, @RequestBody Board edition){
           Board patched = boardService.edit(board_id,username,edition);
        return ResponseEntity.status(HttpStatus.OK).body(patched);
    }



}
