package com.project.authorservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "Authors")
public class Author {

    @Version
    private Long version;

    @Id
    @Field(targetType = FieldType.STRING)
    private UUID id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Indexed(unique = true)
    @Field("NSID")
    private String nsid;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Field("contact_info")
    private ContactInfo contactInfo;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

    @Field("deleted_at")
    private LocalDateTime deletedAt;

}


