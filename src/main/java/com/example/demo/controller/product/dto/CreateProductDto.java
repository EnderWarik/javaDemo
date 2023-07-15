package com.example.demo.controller.product.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateProductDto {

    private String title;

    private Long price;

    private UUID categoryId;
}
