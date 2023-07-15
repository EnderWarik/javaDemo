package com.example.demo.service.category.argument;

import com.example.demo.model.Product;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UpdateCategoryArgument {
    String title;
    List<Product> products;
}
