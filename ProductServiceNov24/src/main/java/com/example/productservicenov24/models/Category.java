package com.example.productservicenov24.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseModel{

    private String title;

    @OneToMany(mappedBy = "Category", cascade = {CascadeType.REMOVE}) // Foreign key constraint delete all the product
    @JsonIgnore
    List<Product> products;

}
