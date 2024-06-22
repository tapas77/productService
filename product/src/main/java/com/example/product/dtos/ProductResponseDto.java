package com.example.product.dtos;

import com.example.product.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String tittle;
    private int price;
    private String description;
    private String image;
    private String category;
}
