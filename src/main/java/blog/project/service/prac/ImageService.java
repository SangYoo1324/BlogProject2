package blog.project.service.prac;

import blog.project.entity.prac.Image;
import blog.project.repository.prac.ImageReposiotory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

//C:\Users\samue\OneDrive\Documents
@Service
@Slf4j
public class ImageService {
    @Autowired
    ImageReposiotory imageReposiotory;

    public final Path rootLocation = Paths.get("C:\\Users\\samue\\OneDrive\\Documents\\image");


    public Image store(MultipartFile file) throws Exception {// 호출할 메소드에게 처리하라고 떠맡김
        try{
            if(file.isEmpty()){
                    throw new Exception("fail to store empty file"); //강제 에러발생(리턴문 필요 없이 걍 종료)
            }
            String saveImageName = fileSave(rootLocation.toString(),file);
            Image saveImage = new Image();
            saveImage.setFileName(file.getOriginalFilename());
            saveImage.setSaveFileName(saveImageName);
            saveImage.setContentType(file.getContentType());
            saveImage.setSize(file.getResource().contentLength());
            saveImage.setRegisterDate(LocalDateTime.now());
            saveImage.setFilePath(rootLocation.toString()+"/"+saveImageName);
            imageReposiotory.save(saveImage);
            return saveImage;
        }catch(Exception e){
            log.info("파일 경로가 잘못된것같은디유");
            throw new Exception("종료한다");//강제 에러 발생 (리턴문 필요 없이 걍 종료)
        }

    }

    private String fileSave(String rootLocation,MultipartFile file) throws Exception{
    File uploadDir = new File(rootLocation.toString());// image 폴더 까지의 경로만
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        String saveFileName= uuid.toString()+file.getOriginalFilename();// 고유 이름
        File saveFile = new File(rootLocation,saveFileName);// 고유 이름으로 바꿔친 컨텐츠는 empty인 파일 생성
        FileCopyUtils.copy(file.getBytes(),saveFile);  // 빈 saveFile에 컨텐츠 복사

        return saveFileName;

    }

    public Image load(Long fileId) {
        return imageReposiotory.findById(fileId).orElse(null);
    }
}
