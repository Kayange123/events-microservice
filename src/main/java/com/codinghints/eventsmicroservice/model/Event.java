package com.codinghints.eventsmicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data @Table(name = "events")
@NoArgsConstructor @AllArgsConstructor @Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    private Date startDateTime;
    private Date endDateTime;
    private String location;
    private String imageUrl;
    private BigDecimal price;
    private String url;
    private Boolean isFree;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Order> orders;

    private Date createdAt;
    private Date updatedAt;
}
