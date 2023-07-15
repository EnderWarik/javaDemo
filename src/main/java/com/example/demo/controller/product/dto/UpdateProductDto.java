package com.example.demo.controller.product.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateProductDto {

    private String title;

    private Long price;

    private UUID categoryId;
}
