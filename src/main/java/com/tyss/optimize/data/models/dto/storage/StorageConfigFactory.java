package com.tyss.optimize.data.models.dto.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class StorageConfigFactory {

	private static Map<String, StorageManager> storageCache;
	
	@Autowired
	public StorageConfigFactory(ApplicationContext context) {

		storageCache = context.getBeansOfType(StorageManager.class);
	}

	public static StorageManager getStorageManager(String type) {

		if (null != storageCache && storageCache.containsKey(type) ) {
			
			return storageCache.get(type);
		}
		
		return storageCache.get("sharedDrive");
	}

	public StorageManager getStorageManagerNonStatic(String type) {

		if (null != storageCache && storageCache.containsKey(type) ) {

			return storageCache.get(type);
		}

		return storageCache.get("sharedDrive");
	}

}
