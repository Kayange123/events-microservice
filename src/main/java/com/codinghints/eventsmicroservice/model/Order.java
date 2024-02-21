package com.codinghints.eventsmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity @Builder @Setter @Getter @AllArgsConstructor
@NoArgsConstructor @Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String stripeId;
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private Date createdAt;
    private Date updatedAt;
}
