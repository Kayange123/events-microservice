package com.codinghints.eventsmicroservice.entity;

import com.codinghints.eventsmicroservice.domain.RequestContext;
import com.codinghints.eventsmicroservice.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

/**
 * @author Mr kayange
 * @version 1.0
 * @email kayangejr3@gmail.com
 * @since May 2024
 */

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class Auditable {
    @Id
    @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_key_seq")
    private Long id;
    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();
    @NotNull
    private Long createdBy;
    @NotNull
    private Long updatedBy;
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedDate
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist(){
        var userId = 0L; // RequestContext.getUserId();
        /*if(userId == null){
            throw new ApiException("Can not persist an entity without user ID in RequestContext in this thread");
        }*/
        setCreatedAt(LocalDateTime.now());
        setCreatedBy(userId);
        setUpdatedBy(userId);
        setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void beforeUpdate(){
        var userId = 0L; //RequestContext.getUserId();
        /*if(userId == null){
            throw new ApiException("Can not update an entity without user ID in RequestContext in this thread");
        }*/
        setUpdatedBy(userId);
        setUpdatedAt(LocalDateTime.now());
    }
}
