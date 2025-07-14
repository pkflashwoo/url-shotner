package com.tiny_url.url_shortner.repository;

import com.tiny_url.url_shortner.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    boolean existsByUniqueCode(String uniqueCode);
}