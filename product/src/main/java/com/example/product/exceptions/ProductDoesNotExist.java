package com.example.product.exceptions;

public class ProductDoesNotExist extends Exception {
    public ProductDoesNotExist(String message) {
        super(message);
    }
}
