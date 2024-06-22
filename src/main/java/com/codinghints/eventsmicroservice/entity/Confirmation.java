package com.codinghints.eventsmicroservice.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "confirmations")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Confirmation extends Auditable{
    private String key;
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private UserEntity userEntity;

    public Confirmation(UserEntity userEntity){
        this.userEntity = userEntity;
        this.key = UUID.randomUUID().toString();
    }
}
