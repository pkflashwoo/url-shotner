package com.tiny_url.url_shortner.models;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "urlMapping")
public class UrlMap {
    @Id
    String id;
    String shortUrl;
    String orignalUrl;
    private java.time.LocalDateTime createdAt;
}
