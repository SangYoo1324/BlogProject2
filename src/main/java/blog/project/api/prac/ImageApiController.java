package blog.project.api.prac;

import blog.project.entity.prac.Image;
import blog.project.service.prac.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageApiController {
    @Autowired
    ImageService imageService;

    @Autowired
    ResourceLoader resourceLoader;

    @PostMapping("/image")
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file){
        try{
            //이미지서비스를 이용해 파일을 로컬 폴더에 저장
            Image image = imageService.store(file);
            return ResponseEntity.status(HttpStatus.OK).body("/image/"+image.getId());// body()에 api주소를 넣으면 api주소 메서드가 실행
            // drawImage 요청을 보내는것 // image 파일 자체는 여기서 저장만 함
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @GetMapping("/image/{fileId}")
    public ResponseEntity<?> drawImage(@PathVariable Long fileId){
        try{
            Image image = imageService.load(fileId); //이미지서비스를 통해 imageRepository로 이미지 가져옴
            Resource resource = resourceLoader.getResource("file:"+image.getFilePath());//"file:"절대경로
            return ResponseEntity.ok().body(resource);
        }catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
