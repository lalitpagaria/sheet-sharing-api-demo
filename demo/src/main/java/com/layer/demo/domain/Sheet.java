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
@Table(name = "sheets", indexes = { @Index(columnList = "file_id,sheet_name", unique = true)})
public class Sheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sheet_id")
    private Long sheetId;

    @ManyToOne
    @JoinColumn(name ="file_id")
    private File file;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "sheet_name")
    private String sheetName;

    @Column(name = "sheet_order")
    private Integer sheetOrder;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(name = "modified_at", nullable = false)
    @LastModifiedDate
    private Timestamp modifiedAt;
}
