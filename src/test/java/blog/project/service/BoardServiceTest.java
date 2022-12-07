package blog.project.service;

import blog.project.entity.Board;
import blog.project.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void topFourPosts() {
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
        for(int i = boardList.size()-1; i>4; i--){
            boardList.remove(i);
        }

        System.out.println("shibal"+boardList.size());

    }
}