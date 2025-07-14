package com.tiny_url.url_shortner.repository;

import com.tiny_url.url_shortner.models.ConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepo extends MongoRepository<ConfigEntity,String> {
    public ConfigEntity findByConfigKey(String key);
}
