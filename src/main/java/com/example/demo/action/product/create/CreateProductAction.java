package com.example.demo.action.product.create;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.argument.CreateProductArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateProductAction {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Product execute(CreateProductActionArgument argument) {

        Category category = categoryService.getExisting(argument.getCategoryId());

        return productService.create(CreateProductArgument.builder()
                                                          .price(argument.getPrice())
                                                          .title(argument.getTitle())
                                                          .category(category)
                                                          .build());
    }
}
