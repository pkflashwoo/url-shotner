package com.tiny_url.url_shortner.controller;


import com.tiny_url.url_shortner.models.ShortUrlResponseModel;
import com.tiny_url.url_shortner.models.UrlShortnerRequestModel;
import com.tiny_url.url_shortner.service.UrlShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/url")
public class UrlShortnerController {
    @Autowired
    private UrlShortnerService shortnerService;

    @PostMapping
    public ResponseEntity<ShortUrlResponseModel> getShortUrl(@RequestBody UrlShortnerRequestModel urlShortnerRequestModel){
        return new ResponseEntity<>(shortnerService.getShortUrl(urlShortnerRequestModel.getUrl()), HttpStatus.OK);
    }
}
