package com.example.demo.service.product.argument;

import com.example.demo.model.Category;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateProductArgument {
    String title;

    Long price;

    Category category;
}
