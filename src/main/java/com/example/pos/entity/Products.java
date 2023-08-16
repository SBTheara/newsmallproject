package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;
    private String name;
    private String description;
    private int category_id;
    private int quantity;
    private String brand;
    private String model;
    private String configuration;
    private double price;
    private int feature;
    private int Create_at;
    @Lob
    private byte[] image;
    private String imageFilePath;
    private String imageName;

}
