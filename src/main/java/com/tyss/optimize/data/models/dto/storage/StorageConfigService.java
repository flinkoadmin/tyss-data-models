package com.tyss.optimize.data.models.dto.storage;

import com.mongodb.client.MongoClient;
import com.tyss.optimize.common.model.auth.AccessTokenMapper;
import com.tyss.optimize.common.util.CommonConstants;
import com.tyss.optimize.data.models.db.service.MongoUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class StorageConfigService {

    @Autowired
    private MongoUtilService mongoUtilService;

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier(value = "mongoClient")
    MongoClient mongoClient;

    @Autowired
    SecureUtil secureUtil;

    public StorageConfig getStorageConfig() throws Exception {
        AccessTokenMapper actionTokenMapper = mongoUtilService.fetchUserAuthData();
        if (!actionTokenMapper.isValid()) {
            log.error("TOKEN_INVALID " + actionTokenMapper);
        }
        String dbPrefix = env.getProperty(CommonConstants.DB_PREFIX);
        String licenseDB = dbPrefix + actionTokenMapper.getLicenseId();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, licenseDB);
        Query executionQuery = Query.query(Criteria.where("licenseId").is(actionTokenMapper.getLicenseId()));
        StorageConfig storageConfigData = mongoTemplate.findOne(executionQuery, StorageConfig.class);
        return setStorageConfig(storageConfigData);
    }

    public StorageConfig getStorageConfig(StorageConfig storageConfigData) throws Exception {
        return setStorageConfig(storageConfigData);
    }

    private StorageConfig setStorageConfig(StorageConfig storageConfig) throws Exception {
        if (storageConfig.getType().equalsIgnoreCase("cloudS3")) {
            if (storageConfig.getAccessKey() != null && storageConfig.getSecretKey() != null) {
                storageConfig.setAccessKey(secureUtil.decrypt(storageConfig.getAccessKey()));
                storageConfig.setSecretKey(secureUtil.decrypt(storageConfig.getSecretKey()));
            }
            return storageConfig;
        }
        if (storageConfig.getType().equalsIgnoreCase("sharedDrive")) {
            if (storageConfig.getUsername() != null && storageConfig.getPassword() != null) {
                storageConfig.setUsername(secureUtil.decrypt(storageConfig.getUsername()));
                storageConfig.setPassword(secureUtil.decrypt(storageConfig.getPassword()));
            }
            return storageConfig;
        }
        return storageConfig;
    }

}
