package com.khs.shop.item.service;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    public String uploadFile(String uploadfilePath,String oriFileName,byte[]fileDate) throws IOException {
        UUID uuid=UUID.randomUUID();
        String extension=oriFileName.substring(oriFileName.lastIndexOf("."));
        String savedFileName=uuid.toString()+extension;
        String fileUploadUrl=uploadfilePath+"/"+savedFileName;
        FileOutputStream fos=new FileOutputStream(fileUploadUrl);
        fos.write(fileDate);
        fos.close();

        return savedFileName;
    }

    public void deleteFile(String filePath){
        File deleteFile=new File(filePath);

        if (deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제 했습니다.");
        }else {
            log.info("파일을 존재하지 않습니다.");

        }

    }
}
