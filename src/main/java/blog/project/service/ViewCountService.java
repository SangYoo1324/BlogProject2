package blog.project.service;

import blog.project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViewCountService {
    @Autowired
   private BoardRepository boardRepository;

    @Transactional
    public void updateView(Long id){
       boardRepository.updateView(id);
    }
}
