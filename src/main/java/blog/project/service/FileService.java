package blog.project.service;

import blog.project.dto.FileDto;
import blog.project.entity.Board;
import blog.project.entity.File;
import blog.project.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static blog.project.api.FileApiController.tempFileQueue;

@Service
@Slf4j
public class FileService {

    @Autowired
    FileRepository fileRepository;

    //public final Path rootLocation_2 = Paths.get("C:\\Users\\samue\\OneDrive\\Documents\\image");
    public final Path rootLocation_2 = Paths.get("C:\\Users\\samue\\IdeaProjects\\blogProject(ClassicLogin)\\src\\main\\resources\\static\\images\\BoardSaved");


    public void deleteFile(Long board_id) {
        //해당 게시글에 있는 모든 파일 불러옴
       List<File> targetFiles= fileRepository.findByBoardId(board_id);
        if(targetFiles.isEmpty()){
           log.info("시발 삭제할 파일이 없어요");
        }
        else{
            for(File f : targetFiles){
                log.info(f.getFileName().toString());
                fileRepository.deleteById(f.getId());
            }

        }
    };

    public File store(MultipartFile file) throws Exception {
        try{
            if(file.isEmpty()){
                log.info("파일이 비어있네유");
            }
            String saveFileName = fileSave(rootLocation_2.toString(),file);
            File saveFile = new File();
            saveFile.setFileName(file.getOriginalFilename());
            saveFile.setSaveFileName(saveFileName);
            saveFile.setContentType(file.getContentType());
            saveFile.setSize(file.getResource().contentLength());
            saveFile.setRegisterDate(LocalDateTime.now());
            saveFile.setFilePath(rootLocation_2.toString()+"/"+saveFileName);
            saveFile.setBoard(null);
            fileRepository.save(saveFile);
            return saveFile;


        }catch(Exception e){ // contentLength: ioException 때매 쓴다
            log.info("file경로가 잘못된것같습니다");
            //return 필요 없이 종료되게 강제 에러발생 시켜줌
            throw new Exception("오류났으니까 이거 메소드 쓴 객체가 해결하셈"); //throws signature로
            //                                                              쓰는놈한테 처리하라고 떠넘김
        }

    }

    private String fileSave(String toString, MultipartFile file) throws IOException {
        java.io.File uploadDir = new java.io.File(rootLocation_2.toString()); // image폴터까지의 경로만
        if(!uploadDir.exists()){
            uploadDir.mkdirs();// 폴더(directory) 생성(지정한 경로 대로 없는경우 알아서 생성해줌...)
        }
        UUID uuid = UUID.randomUUID();
        String saveFileName = uuid.toString()+file.getOriginalFilename();
        //path만 지정된 빈깡통파일     File(path,filename)
        java.io.File saveFile = new java.io.File(rootLocation_2.toString(),saveFileName);
        FileCopyUtils.copy(file.getBytes(),saveFile);  // IOexception 떠넘김

        return saveFileName;
    }

    public File load(Long targetId) {
        return fileRepository.findById(targetId).orElse(null);
    }


    //File 상대경로만 string으로 반환(view Template 에서 쓰기 위해)
    public List<FileDto> multipleLoad(){
        List<File>files = fileRepository.findAll();
        List<FileDto>relPaths = new ArrayList<>();
        files.stream().forEach(s-> {
                 String[] bracket =    s.getFilePath().split("/");
                    String path =bracket[bracket.length-1];
                    log.info("/images/BoardSaved/"+path);
                    FileDto fileDto = new FileDto();
                    fileDto.setRelPath("/images/BoardSaved/"+path);
                    fileDto.setId(s.getId());
                    relPaths.add(fileDto);
        }
        );

        return relPaths;
    }
    public void injectBoardId(Board board) {
        while(!tempFileQueue.isEmpty()){
            File file = fileRepository.findById(tempFileQueue.poll().getId()).orElse(null);
            file.setBoard(board);
        }
        if(tempFileQueue.isEmpty()){
            log.info("no file attached");
        }

    }
}
