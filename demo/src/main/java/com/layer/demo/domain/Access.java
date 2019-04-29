package com.layer.demo.domain;

import com.layer.demo.model.AccessMatrix;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Entity
@TypeDef(name = "json", typeClass = JsonStringType.class)
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

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private AccessMatrix accessMatrix;

    @NotBlank
    private Boolean isDeleted;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Timestamp createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Timestamp modifiedAt;
}
