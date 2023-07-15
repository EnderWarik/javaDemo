package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.service.product.argument.CreateProductArgument;
import com.example.demo.service.product.argument.SearchProductArgument;
import com.example.demo.service.product.argument.UpdateProductArgument;
import com.querydsl.core.types.Predicate;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> list(SearchProductArgument argument);

    Product create(CreateProductArgument argument);

    Product getExisting(UUID id);

    Product update(UUID id, UpdateProductArgument argument);

    void delete(UUID id);

    Predicate buildPredicate(SearchProductArgument argument);
}
