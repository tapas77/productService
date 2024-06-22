package com.example.product.models;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Getter
@Setter
@Entity
@JsonSerialize
public class Product extends BaseModel implements Serializable {
    private String name;
    private String description;
    private int price;
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
