package com.project.publisherservice.repository;

import com.project.publisherservice.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
}

