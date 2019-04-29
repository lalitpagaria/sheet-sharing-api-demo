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
@Table(name = "sheets")
public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sheetId;

    @ManyToOne
    @JoinColumn(name ="file_id")
    private File file;

    @NotBlank
    @Size(min = 1, max = 255)
    private String sheetName;

    @NotBlank
    private Integer sheetOrder;

    @NotBlank
    private Boolean isDeleted;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Timestamp modifiedAt;
}
