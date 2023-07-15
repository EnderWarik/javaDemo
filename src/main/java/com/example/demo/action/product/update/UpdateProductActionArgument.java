package com.example.demo.action.product.update;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UpdateProductActionArgument {

    String title;

    Long price;

    UUID categoryId;
}
