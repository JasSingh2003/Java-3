package com.example.assignment3_jaskeeratsingh.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class Watch {
    private int watchId;
    private String brand;
    private double price;
    private Integer stockQuantity;
    private String description;
    private double rating;
}
