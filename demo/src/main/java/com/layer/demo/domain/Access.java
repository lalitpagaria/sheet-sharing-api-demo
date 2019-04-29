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
@Table(name = "access")
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessId;

    @ManyToOne
    @JoinColumn(name ="sheet_id")
    private Sheet sheet;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;

    @NotBlank
    @Size(max = 8)
    private Byte[] readAccessBitmap;

    @NotBlank
    @Size(max = 8)
    private Byte[] writeAccessBitmap;

    @NotBlank
    private Boolean isDeleted;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Timestamp modifiedAt;
}
