package com.example.product.dtos;

import com.example.product.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDto {
    private String tittle;
    private int price;
    private String description;
    private String image;
    private String category;
}
