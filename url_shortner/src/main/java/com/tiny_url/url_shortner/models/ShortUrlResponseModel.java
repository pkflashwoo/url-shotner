package com.tiny_url.url_shortner.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrlResponseModel {
    private String longUrl;
    private String shortUrl;
}
