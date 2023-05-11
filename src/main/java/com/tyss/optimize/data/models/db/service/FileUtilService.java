package com.tyss.optimize.data.models.db.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class FileUtilService {

    public boolean verifyFileExtension(MultipartFile file, String extensionType) throws IOException {
        boolean isSameExtension = false;
        if(!file.isEmpty()) {
            ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {

                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }

                @Override
                public long contentLength() {
                    return file.getSize();
                }
            };
            String fileName = fileAsResource.getFilename();
            int index = fileName.lastIndexOf(".");
            String extension = fileName.substring(index+1);
            if(extension.equalsIgnoreCase(extensionType)) {
                isSameExtension = true;
            }
        }
        return isSameExtension;
    }

}
