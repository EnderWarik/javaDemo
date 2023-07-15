package com.example.demo.controller.product.dto;

import com.example.demo.controller.category.dto.CategoryDto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {
    private UUID id;

    private String title;

    private Long price;

    private CategoryDto category;
}
