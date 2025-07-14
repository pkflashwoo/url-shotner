package com.tiny_url.url_shortner.service;

import com.tiny_url.url_shortner.models.UrlMap;
import com.tiny_url.url_shortner.repository.UrlMapRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlFetchingService {

    @Autowired
    private UrlMapRepo urlMapRepo;

    @Cacheable(value = "#id")
    public String getRedirectUrl(String id) {

        Optional<UrlMap> urlMap = urlMapRepo.findById(id);
        return urlMap.get().getOrignalUrl();
    }

}
