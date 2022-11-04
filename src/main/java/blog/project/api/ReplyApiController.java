package blog.project.api;

import blog.project.dto.ReplyDto;
import blog.project.entity.Reply;
import blog.project.repository.ReplyRepository;
import blog.project.service.ReplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ReplyApiController {
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    ReplyService replyService;

    //Getting CommentsList
//댓글 가져오기(Talend Api Test용)
@GetMapping("/api/reply/{board_id}/{username}")
    public List<ReplyDto> getReplyList(@PathVariable Long board_id,//게시글 번호
                                                    @PathVariable String username ){//로그인 아이디
        List<ReplyDto> Replies = replyService.replyList(board_id);
    return Replies;
}

//댓글 생성
    @PostMapping("/api/reply/create/{board_id}/{user_id}")
    public ResponseEntity<ReplyDto> createReply(@PathVariable Long board_id,
                                             @PathVariable Long user_id,
                                             @RequestBody ReplyDto replyDto
                                             ){
    ReplyDto createdReplyDto = replyService.createReply(board_id,user_id,replyDto);
    return ResponseEntity.status(HttpStatus.OK).body(createdReplyDto);
    }
    //댓글 수정
    @PatchMapping("/api/reply/edit/{board_id}/{user_id}/{reply_id}")
    public ResponseEntity<ReplyDto> editReply(@PathVariable Long board_id,
                                              @PathVariable Long user_id,
                                              @PathVariable Long reply_id,
                                              @RequestBody ReplyDto patchDto){
    ReplyDto patchedDto = replyService.editReply(board_id, user_id, reply_id, patchDto);

    return ResponseEntity.status(HttpStatus.OK).body(patchedDto);
    }
    //댓글 삭제
    @DeleteMapping("/api/reply/delete/{board_id}/{user_id}/{reply_id}")
    public ResponseEntity<ReplyDto> deleteReply(@PathVariable Long board_id,
                                                @PathVariable Long user_id,
                                                @PathVariable Long reply_id){
    ReplyDto deleted = replyService.deleteReply(board_id,user_id,reply_id);

    return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
