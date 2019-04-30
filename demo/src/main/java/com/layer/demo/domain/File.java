package com.layer.demo.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileId;

    @NotBlank
    @Column(name = "file_name", unique = true)
    @Size(min = 1, max = 255)
    private String fileName;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "git_repo")
    private String gitRepo;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "uri_path")
    private String uriPath;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "modified_at", nullable = false)
    @LastModifiedDate
    private Timestamp modifiedAt;
}
