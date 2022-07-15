package com.tyss.optimize.data.models.db.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends CrudRepository<UserCache, String> {

}