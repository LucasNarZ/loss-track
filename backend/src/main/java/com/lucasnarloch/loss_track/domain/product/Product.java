package com.lucasnarloch.loss_track.domain.product;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String barcode;
    private String category;

    protected Product(){}

    public Product(String name, String barcode, String category) {
        this.name = name;
        this.barcode = barcode;
        this.category = category;
    }

    public void update(String name, String barcode, String category) {
        this.name = name;
        this.barcode = barcode;
        this.category = category;
    }

}
