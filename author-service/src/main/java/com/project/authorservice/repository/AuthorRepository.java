package com.project.authorservice.repository;

import com.project.authorservice.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends MongoRepository<Author, UUID> {
    Optional<Author> findByNsid(String nsid);
}

