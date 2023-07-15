package com.example.demo.action.product.update;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.argument.UpdateProductArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateProductAction {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Product execute(UUID id, UpdateProductActionArgument argument) {

        Category category = categoryService.getExisting(argument.getCategoryId());

        return productService.update(id, UpdateProductArgument.builder()
                                                              .price(argument.getPrice())
                                                              .title(argument.getTitle())
                                                              .category(category)
                                                              .build());
    }
}
