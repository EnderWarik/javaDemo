package com.example.demo.action.product.create;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreateProductActionArgument {
    String title;

    Long price;

    UUID categoryId;
}
