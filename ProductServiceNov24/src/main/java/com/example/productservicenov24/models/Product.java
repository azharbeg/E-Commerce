package com.example.productservicenov24.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel{



    private String title;

    private String description;

    private double price;

    @ManyToOne(cascade = {CascadeType.PERSIST})// If new product created and that category is not there please create that category
    private Category category;
    private String imageUrl;
}
