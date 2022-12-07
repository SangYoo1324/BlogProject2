package blog.project.api;

import blog.project.entity.File;
import blog.project.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.Queue;

@RestController
public class FileApiController {
    public static Queue<File> tempFileQueue = new LinkedList<File>();

    @Autowired
    FileService fileService;
    @Autowired
    ResourceLoader resourceLoader;

    @PostMapping("/file")
    public ResponseEntity<?> fileUpload(@RequestParam("file")MultipartFile file){
        //                               RequestParam 은 form이나 input 데이터  "name" 속성값을 가져올 수 도 있음
        // json 데이터로 보내줄 때 formdata type으로 보내니 form 타입 값을 받아오므로 requestparam 가능
        //formdata 에 name= "file"인 값을 가져온다는 뜻
        try{
            //fileService를 이용해 파일 db밑 로컬 폴더에 저장
            File saveFile = fileService.store(file);
            tempFileQueue.add(saveFile);//포스트에서 사용한 이미지 id를 다 queue에 밀어넣음
            return ResponseEntity.status(HttpStatus.OK).body("/file/"+saveFile.getId());
            //body의 주소는 serveImage메소드 주소와 연결

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/file/{targetId}")// "file/n" 주소에 resource를 그려주는 역할
    //                                  실제로 이 주소 타고 가면 이미지가 나옴
    public ResponseEntity<?> serveFile(@PathVariable Long targetId){
        try{
            File file = fileService.load(targetId);// db에서 저장된 파일 불러옴
            Resource resource = resourceLoader.getResource("file:"+file.getFilePath());//"file:
            //                                                                                  절대경로
            return ResponseEntity.status(HttpStatus.OK).body(resource);// 주소 타고 가면 리소스가 뜸

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
