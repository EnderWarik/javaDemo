package com.example.demo.controller.product.dto;

import lombok.Data;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SearchProductDto {

    private String productName;
    private String categoryName;
}
