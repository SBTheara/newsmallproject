package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sales")
@Data
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sale_id;
    private int order_id;
    private int sales_amount;
    private int create_at;
}
