package com.codinghints.eventsmicroservice.entity;

import com.codinghints.eventsmicroservice.enumeration.Authority;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RoleEntity extends Auditable{
    private String name;
    private Authority authorities;
}
