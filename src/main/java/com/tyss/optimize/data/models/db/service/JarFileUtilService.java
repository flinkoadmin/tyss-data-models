package com.tyss.optimize.data.models.db.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class JarFileUtilService {

    @Autowired
    Environment env;
    public List<String> getMatchedDefaultJarFiles(List<MultipartFile> inputFiles) {
        List<String> jarList = new ArrayList<>();
        String workFolder = env.getProperty("optimize.temp.dir");
        String jarFilePath = workFolder+"lib/";
        File jarFolder = new File(jarFilePath);

        if (jarFolder.isDirectory()) {
            File[] serverFiles = jarFolder.listFiles();
            if(serverFiles.length>0){
                List<String> fileList = new ArrayList<>();
                for(File file: serverFiles){
                    fileList.add(FilenameUtils.getBaseName(file.getName()));
                }
                for (MultipartFile file : inputFiles) {
                    String fileName = FilenameUtils.getBaseName(file.getOriginalFilename());
                    if(fileList.contains(fileName)) {
                        jarList.add(fileName);
                    }
                }
            }
        }
        return jarList;
    }

    public boolean isDefaultJarFile(String inputFileName) {
        String workFolder = env.getProperty("optimize.temp.dir");
        String jarFilePath = workFolder+"lib/";
        File jarFolder = new File(jarFilePath);

        if (jarFolder.isDirectory()) {
            File[] serverFiles = jarFolder.listFiles();
            if(serverFiles.length>0){
                List<String> fileList = new ArrayList<>();
                for(File file: serverFiles){
                    fileList.add(FilenameUtils.getBaseName(file.getName()));
                }
                String fileName = FilenameUtils.getBaseName(inputFileName);
                if(fileList.contains(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }

}
