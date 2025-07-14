package com.tiny_url.url_shortner.models;

import com.tiny_url.url_shortner.util.CodeGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String uniqueCode;
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.uniqueCode = CodeGenerator.generateUniqueCode();
    }
}