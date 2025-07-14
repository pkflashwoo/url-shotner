package com.tiny_url.url_shortner.service;

import com.tiny_url.url_shortner.models.ShortUrlResponseModel;
import com.tiny_url.url_shortner.models.UrlMap;
import com.tiny_url.url_shortner.repository.ConfigRepo;
import com.tiny_url.url_shortner.repository.UrlMapRepo;
import com.tiny_url.url_shortner.util.CodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UrlShortnerService {

    @Autowired
    private UrlMapRepo urlMapRepo;

    @Autowired
    private ConfigRepo configRepo;

    public ShortUrlResponseModel getShortUrl(String url){
        String shortUrl = "";
        try {

            UrlMap lastRow = urlMapRepo.findTopByOrderByCreatedAtDesc();
            String lastCode = lastRow != null ? lastRow.getId() : null;
            String uid = CodeGenerator.getNextCode(lastCode);
            shortUrl = getDns() + "/" + uid;
            UrlMap urlMapModel = UrlMap.builder().orignalUrl(url)
                    .shortUrl(shortUrl).id(uid)
                    .createdAt(java.time.LocalDateTime.now()).build();
            urlMapRepo.save(urlMapModel);
        }
        catch (Exception e){
            log.error("Got exception  : {}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return ShortUrlResponseModel.builder().longUrl(url).shortUrl(shortUrl).build();
    }

    private String getDns(){
        return configRepo.findByConfigKey("TinyUrlDns").getConfigValue();
    }
    
    public UrlMap getLastInsertedUrlMap() {
        return urlMapRepo.findTopByOrderByCreatedAtDesc();
    }
}
