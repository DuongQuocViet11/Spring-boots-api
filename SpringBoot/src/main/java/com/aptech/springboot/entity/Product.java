package com.aptech.springboot.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")

public class Product {
    @Id
    private String productId;
    private String productName;
    private String slug;
    private String description;
    private String detail;
    private double price;
    private String thumbnail;
    private String producer;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private Date deletedAt;
    private String createdBy;
    private String updatedBy;
    private String deletedBy;
}
