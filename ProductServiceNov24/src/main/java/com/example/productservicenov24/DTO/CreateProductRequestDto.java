package com.example.productservicenov24.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {

    // this class excately show what I received request from the client to create the product

    // it's upon to us what do we want to receive
    private String title;
    private String description;

    private String image;
    private String category;
    private double price;



}
