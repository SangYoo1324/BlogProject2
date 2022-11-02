package blog.project.service;

import blog.project.dto.ReplyDto;
import blog.project.entity.Board;
import blog.project.entity.Reply;
import blog.project.entity.User;
import blog.project.repository.BoardRepository;
import blog.project.repository.ReplyRepository;
import blog.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public List<ReplyDto> replyList(Long board_id) {
        List<ReplyDto> replyList = replyRepository.findByBoardId(board_id)
                .stream().map(r->r.toDto()).collect(Collectors.toList());


        return replyList;
    }
@Transactional
    public ReplyDto createReply(Long board_id,Long user_id, ReplyDto replyDto) {
        //get userEntity , boardEntity
        User poster = userRepository.findById(user_id).orElse(null);
        Board board = boardRepository.findById(board_id).orElse(null);
        // replyDto to replyEntity
        Reply postTarget = replyDto.toEntity(poster,board);
        log.info(postTarget.toString());
        //save by reply repository
        replyRepository.save(postTarget);
           ReplyDto returnCreatedDto = postTarget.toDto();
           log.info("확인용"+returnCreatedDto.toString());
        //return replyEntity to replyDto and return
        return returnCreatedDto;
    }

    @Transactional
    public ReplyDto deleteReply(Long board_id, Long user_id,Long reply_id) {
        //db에서 삭제할 값 꺼내옴
        Reply target = replyRepository.findById(reply_id).orElse(null);
        replyRepository.delete(target);

        return target.toDto();
    }
}
