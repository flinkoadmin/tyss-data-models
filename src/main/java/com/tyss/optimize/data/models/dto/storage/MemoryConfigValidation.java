package com.tyss.optimize.data.models.dto.storage;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.common.util.MongoCollections;
import com.tyss.optimize.data.models.db.model.LicensePrivilege;
import com.tyss.optimize.data.models.db.model.MemoryConfig;
import com.tyss.optimize.data.models.db.model.Project;
import com.tyss.optimize.data.models.db.service.MongoUtilService;
import com.tyss.optimize.data.models.dto.ResponseDTO;
import com.tyss.optimize.data.models.dto.StorageInfo;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class MemoryConfigValidation {

    @Autowired
    @Qualifier(value = "mongoClient")
    MongoClient mongoClient;

    @Autowired
    @Qualifier(value = "mongoAuthTemplate")
    MongoTemplate mongoAuthTemplate;

    @Autowired
    private MongoUtilService mongoUtilService;

    @Autowired
    private Environment env;

    public ResponseDTO projectValidation(String licenseId, LicensePrivilege licensePrivilege, Project proj) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Document> documentList = getMongoTemplate(licenseId).findAll(Document.class, MongoCollections.projects.name());
        List<Document> memoryConfigDoc = getMongoTemplate(licenseId).findAll(Document.class, MongoCollections.memory_config.name());
        Gson gson = new Gson();
        AtomicLong totalIncrementalAssign = new AtomicLong();
        
        //--------new code-------------//
        boolean isNotAllowedToProceed = false;
        String message = null;
        long projectsCount = licensePrivilege.getPrivilege().getProject().getCount();
        if (documentList.size() >= projectsCount) {
            isNotAllowedToProceed = true;
            message = "Exceeded projects count";
        }

//        List<Roles> roles = getMongoTemplate(licenseId).findAll(Roles.class);
//        long rolesCount = licensePrivilege.getPrivilege().getProject().getRoles();
//        if (roles.size() > rolesCount) {
//            isNotAllowedToProceed = true;
//            message = "Exceeded roles count";
//        }

        boolean isAllowedProject = licensePrivilege.getPrivilege().getProject().getAllowedProjects().contains(proj.getType());
        if (!isAllowedProject) {
            isNotAllowedToProceed = true;
            message = proj.getType() + " project-type is not allowed";
        }

        if (isNotAllowedToProceed) {
            responseDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage(message);
            responseDTO.setStatus(CommonConstants.FAILURE);
            return responseDTO;
        }
        //--------new code ends-------------//
        
        if (memoryConfigDoc.size() > 0) {
            MemoryConfig memoryConfig = gson.fromJson(memoryConfigDoc.get(0).toJson(), MemoryConfig.class);
            AtomicLong usedMemory = new AtomicLong();
            if (documentList.size() > 0) {
                documentList.stream().forEach(doc -> {
                    Project project = gson.fromJson(doc.toJson(), Project.class);
                    String projectAssignedMemory = Objects.nonNull(project.getAssignedMemory()) ? project.getAssignedMemory() : "200 MB";
                    usedMemory.set(totalIncrementalAssign.addAndGet(convertToBytes(projectAssignedMemory)));
                });
                long totalMemory = convertToBytes(memoryConfig.getTotalMemory() + " " + memoryConfig.getTotalMemoryUnit());
                long minimumMemory = convertToBytes(CommonConstants.DEFAULT_PROJECT_MEMORY + " " + CommonConstants.DEFAULT_MEMORY_UNIT);
                long remainingMemory = totalMemory - usedMemory.get();
                if (totalMemory < usedMemory.get() || remainingMemory < minimumMemory) {
                    responseDTO.setResponseCode(HttpStatus.INSUFFICIENT_STORAGE.value());
                    responseDTO.setMessage("Not enough memory to create new project");
                    responseDTO.setStatus(CommonConstants.FAILURE);
                    return responseDTO;
                } else
                    responseDTO.setStatus(CommonConstants.SUCCESS);
            } else
                responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

    public ResponseDTO projectValidation(String licenseId) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Document> document = getMongoTemplate(licenseId).findAll(Document.class, MongoCollections.projects.name());
        List<Document> memoryConfigDoc = getMongoTemplate(licenseId).findAll(Document.class, MongoCollections.memory_config.name());
        Gson gson = new Gson();
        AtomicLong totalIncrementalAssign = new AtomicLong();
        if (memoryConfigDoc.size() > 0) {
            MemoryConfig memoryConfig = gson.fromJson(memoryConfigDoc.get(0).toJson(), MemoryConfig.class);
            AtomicLong usedMemory = new AtomicLong();
            if (document.size() > 0) {
                document.stream().forEach(doc -> {
                    Project project = gson.fromJson(doc.toJson(), Project.class);
                    String projectAssignedMemory = Objects.nonNull(project.getAssignedMemory()) ? project.getAssignedMemory() : "200 MB";

                        usedMemory.set(totalIncrementalAssign.addAndGet(convertToBytes(projectAssignedMemory)));
});
        long totalMemory = convertToBytes(memoryConfig.getTotalMemory() + " " + memoryConfig.getTotalMemoryUnit());
        long minimumMemory = convertToBytes(CommonConstants.DEFAULT_PROJECT_MEMORY + " " + CommonConstants.DEFAULT_MEMORY_UNIT);
        long remainingMemory = totalMemory - usedMemory.get();
        if (totalMemory < usedMemory.get() || remainingMemory < minimumMemory) {
        responseDTO.setResponseCode(HttpStatus.INSUFFICIENT_STORAGE.value());
        responseDTO.setMessage("Not enough memory to create new project");
        responseDTO.setStatus(CommonConstants.FAILURE);
        return responseDTO;
        } else
        responseDTO.setStatus(CommonConstants.SUCCESS);
        } else
        responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
        }

    public ResponseDTO fileValidation(String projectId, String licenseId, List<MultipartFile> files) {
        ResponseDTO responseDTO = new ResponseDTO();
        Query projectDetailQuery = new Query(Criteria.where("_id").is(projectId));
        Document document = getMongoTemplate(licenseId).findOne(projectDetailQuery, Document.class, MongoCollections.projects.name());

        if (Objects.nonNull(document)) {
            Gson gson = new Gson();
            Project project = gson.fromJson(document.toJson(), Project.class);
            String projectAssignedMemory = Objects.nonNull(project.getAssignedMemory()) ? project.getAssignedMemory() : "200 MB";
            String projectUsedMemory = Objects.nonNull(project.getConsumedMemory()) ? project.getConsumedMemory() : "0 MB";
            long assignedMemory = convertToBytes(projectAssignedMemory);
            long usedMemory = convertToBytes(projectUsedMemory);
            long remainingMemory = assignedMemory - usedMemory;
            long memory = files.stream().mapToLong(MultipartFile::getSize).sum();
                if (remainingMemory < memory) {
                    responseDTO.setResponseCode(HttpStatus.INSUFFICIENT_STORAGE.value());
                    responseDTO.setMessage("Not enough memory to add file");
                    responseDTO.setStatus(CommonConstants.FAILURE);
                    return responseDTO;
                } else
                    responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

    public ResponseDTO fileValidation(String projectId, String licenseId, StorageInfo storageInfoFile) {
        log.info("Checking storage availability for storing file on cloud");
        ResponseDTO responseDTO = new ResponseDTO();
        Query projectDetailQuery = new Query(Criteria.where("_id").is(projectId));
        Document document = getMongoTemplate(licenseId).findOne(projectDetailQuery, Document.class, MongoCollections.projects.name());

        if (Objects.nonNull(document)) {
            Gson gson = new Gson();
            Project project = gson.fromJson(document.toJson(), Project.class);
            String projectAssignedMemory = Objects.nonNull(project.getAssignedMemory()) ? project.getAssignedMemory() : "200 MB";
            String projectUsedMemory = Objects.nonNull(project.getConsumedMemory()) ? project.getConsumedMemory() : "0 MB";
            long assignedMemory = convertToBytes(projectAssignedMemory);
            long usedMemory = convertToBytes(projectUsedMemory);
            long remainingMemory = assignedMemory - usedMemory;
            StorageManager storageManager = StorageConfigFactory.getStorageManager(storageInfoFile.getType());
            long memory = storageManager.getFileSize(storageInfoFile);
            if (remainingMemory < memory) {
                log.error("Not enough memory to add file");
                responseDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Not enough memory to add file");
                responseDTO.setStatus(CommonConstants.FAILURE);
                return responseDTO;
            } else
                responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

    public ResponseDTO folderValidation(String projectId, String licenseId, StorageInfo storageInfoFile) {
        log.info("Checking storage availability for storing file on cloud");
        ResponseDTO responseDTO = new ResponseDTO();
        Query projectDetailQuery = new Query(Criteria.where("_id").is(projectId));
        Document document = getMongoTemplate(licenseId).findOne(projectDetailQuery, Document.class, MongoCollections.projects.name());

        if (Objects.nonNull(document)) {
            Gson gson = new Gson();
            Project project = gson.fromJson(document.toJson(), Project.class);
            String projectAssignedMemory = Objects.nonNull(project.getAssignedMemory()) ? project.getAssignedMemory() : "200 MB";
            String projectUsedMemory = Objects.nonNull(project.getConsumedMemory()) ? project.getConsumedMemory() : "0 MB";
            long assignedMemory = convertToBytes(projectAssignedMemory);
            long usedMemory = convertToBytes(projectUsedMemory);
            long remainingMemory = assignedMemory - usedMemory;
            StorageManager storageManager = StorageConfigFactory.getStorageManager(storageInfoFile.getType());
            long memory = storageManager.getFolderSize(storageInfoFile);
            if (remainingMemory < memory) {
                log.error("Not enough memory to add folder");
                responseDTO.setResponseCode(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Not enough memory to add folder");
                responseDTO.setStatus(CommonConstants.FAILURE);
                return responseDTO;
            } else
                responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

    public ResponseDTO s3FileValidation(String projectId, MultipartFile file, String licenseId, long size) {
        ResponseDTO responseDTO = new ResponseDTO();
        Query projectDetailQuery = new Query(Criteria.where("_id").is(projectId));
        Document document = getMongoTemplate(licenseId).findOne(projectDetailQuery, Document.class, MongoCollections.projects.name());

        if (Objects.nonNull(document)) {
            Gson gson = new Gson();
            Project project = gson.fromJson(document.toJson(), Project.class);
            String projectAssignedMemory = Objects.nonNull(project.getAssignedMemory()) ? project.getAssignedMemory() : "200 MB";
            String projectUsedMemory = Objects.nonNull(project.getConsumedMemory()) ? project.getConsumedMemory() : "0 MB";
            long assignedMemory = convertToBytes(projectAssignedMemory);
            long usedMemory = convertToBytes(projectUsedMemory);
            long remainingMemory = assignedMemory - usedMemory;
            long minMemory = file.getSize() - size;
            if (remainingMemory < file.getSize() && remainingMemory < minMemory) {
                responseDTO.setResponseCode(HttpStatus.INSUFFICIENT_STORAGE.value());
                responseDTO.setMessage("Not enough memory to add file");
                responseDTO.setStatus(CommonConstants.FAILURE);
                return responseDTO;
            } else
                responseDTO.setStatus(CommonConstants.SUCCESS);
        }
        return responseDTO;
    }

    public static long convertToBytes(String value) {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;
        String splitValue = value.split(" ")[0];
        if (value.contains(CommonConstants.kilo_byte)) {
            return Math.round(Double.parseDouble(splitValue) * kilobyte);
        } else if (value.contains(CommonConstants.mega_byte)) {
            return Math.round(Double.parseDouble(splitValue) * megabyte);
        } else if (value.contains(CommonConstants.giga_byte)) {
            return Math.round(Double.parseDouble(splitValue) * gigabyte);
        } else if (value.contains(CommonConstants.tera_byte)) {
            return Math.round(Double.parseDouble(splitValue) * terabyte);
        }
        return 0;
    }

    private MongoTemplate getMongoTemplate(String licenseId) {
        String dbPrefix = env.getProperty(CommonConstants.DB_PREFIX);
        String licenseDB = dbPrefix + licenseId;
        return new MongoTemplate(mongoClient, licenseDB);
    }
}
