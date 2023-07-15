package com.example.demo.controller.cart.dto;

import com.example.demo.controller.product.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {

    private UUID id;

    private List<ProductDto> products;
}
