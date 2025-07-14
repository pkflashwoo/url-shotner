package com.tiny_url.url_shortner.repository;

import com.tiny_url.url_shortner.models.UrlMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlMapRepo extends MongoRepository<UrlMap,String> {
    UrlMap findTopByOrderByIdDesc();
    UrlMap findTopByOrderByCreatedAtDesc();
}
