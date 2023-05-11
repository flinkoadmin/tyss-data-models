package com.tyss.optimize.data.models.dto.storage;

import com.tyss.optimize.common.util.CommonConstants;
import jcifs.DialectVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.integration.smb.session.SmbSessionFactory;

import java.util.Optional;

@Configuration("sharedDriveConfigOld")
public class SharedDriveConfig {

    @Autowired
    @Qualifier(value = "mongoAuthTemplate")
    private MongoTemplate mongoAuthTemplate;

    @Bean("smbSessionFactoryOld")
    public SmbSessionFactory smbSessionFactory() throws Exception {
        Optional<StorageConfig> storageConfigOptional = mongoAuthTemplate.findAll(StorageConfig.class).stream().findFirst();
        SmbSessionFactory smbSessionFactory = new SmbSessionFactory();
        if (storageConfigOptional.isPresent()) {
            StorageConfig storageConfig = storageConfigOptional.get();
            smbSessionFactory.setHost(storageConfig.getIpAddress());
            smbSessionFactory.setPort(445);
            smbSessionFactory.setDomain("");
            smbSessionFactory.setUsername(storageConfig.getUsername());
            smbSessionFactory.setPassword(storageConfig.getPassword());
            smbSessionFactory.setShareAndDir(storageConfig.getDomain() + CommonConstants.FORWARD_SLASH);
            smbSessionFactory.setSmbMinVersion(DialectVersion.SMB210);
            smbSessionFactory.setSmbMaxVersion(DialectVersion.SMB311);
        }
        return smbSessionFactory;
    }
}
