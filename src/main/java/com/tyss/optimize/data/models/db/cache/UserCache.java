package com.tyss.optimize.data.models.db.cache;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("User")
@Data
public class UserCache implements Serializable {

    private String id;
    private String token;
}