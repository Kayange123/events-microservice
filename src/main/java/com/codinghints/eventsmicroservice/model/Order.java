package com.codinghints.eventsmicroservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity @Builder @Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String stripeId;
    private BigDecimal totalAmount;
//    private String buyerId;
//    private String eventId;

    @ManyToOne(fetch = FetchType.LAZY)

    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    private User buyer;

    private Date createdAt;
    private Date updatedAt;
}
