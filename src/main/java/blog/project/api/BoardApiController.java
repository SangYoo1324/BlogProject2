package blog.project.api;

import blog.project.controller.UserController;
import blog.project.dto.ResponseDto;
import blog.project.model.Board;
import blog.project.model.RoleType;
import blog.project.model.User;
import blog.project.service.BoardService;
import blog.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static blog.project.controller.UserController.isLoggedIn;

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
    @DeleteMapping("/api/board/deletePost/{username}/{boardid}")
    public ResponseEntity<Board> deletePost(@PathVariable Long boardid){
    Board deletedBoard=boardService.delete(boardid);
        return ResponseEntity.status(HttpStatus.OK).body(deletedBoard);
    }

    //Edit Post
    @PatchMapping("/api/board/editPost/{username}/{boardid}")
    public ResponseEntity<Board> deletePost(@PathVariable Long boardid, @PathVariable String username, @RequestBody Board edition){
           Board patched = boardService.edit(boardid,username,edition);
        return ResponseEntity.status(HttpStatus.OK).body(patched);
    }



}
