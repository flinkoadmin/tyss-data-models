package com.tyss.optimize.data.models.db.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CacheService {

    @Autowired
    private UserCacheRepository userCacheRepository;

    public boolean updateUserTokenInCache(UserCache userCache) throws Exception {

        UserCache respUserCache = userCacheRepository.save(userCache);
        if (Objects.nonNull(respUserCache))
        {
            return true;
        }
        return false;
    }

    public boolean removeUserFromCache(UserCache userCache) throws Exception {

        try {
            userCacheRepository.deleteById(userCache.getId());
        }
        catch (Exception exception){
            return false;
        }

        return true;
    }

    public boolean isTokenValid(UserCache userCache) throws Exception {
        Optional<UserCache> respUserCacheOpt = userCacheRepository.findById(userCache.getId());
        if(respUserCacheOpt.isPresent())
        {
            UserCache respUserCache = respUserCacheOpt.get();
            if(StringUtils.isNotEmpty(userCache.getToken()) && StringUtils.isNotEmpty(respUserCache.getToken()) &&
                    userCache.getToken().equals(respUserCache.getToken()))
            {
                return true;
            }
        }
        return false;
    }
}
