package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.model.QProduct;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.product.argument.CreateProductArgument;
import com.example.demo.service.product.argument.SearchProductArgument;
import com.example.demo.service.product.argument.UpdateProductArgument;
import com.example.demo.utils.QPredicates;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final QProduct qProduct = QProduct.product;

    @Override
    @Transactional(readOnly = true)
    public List<Product> list(SearchProductArgument argument) {

        Predicate predicate = buildPredicate(argument);

        return Lists.newArrayList(repository.findAll(predicate));
    }

    public Predicate buildPredicate(SearchProductArgument argument) {
        return QPredicates.builder()
                          .add(argument.getProductName(), qProduct.title::containsIgnoreCase)
                          .add(argument.getCategoryName(), qProduct.category.title::containsIgnoreCase)
                          .buildAnd();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Product create(CreateProductArgument argument) {
        return repository.save(Product.builder()
                                      .title(argument.getTitle())
                                      .price(argument.getPrice())
                                      .category(argument.getCategory())
                                      .build());
    }

    @Override
    @Transactional(readOnly = true)
    public Product getExisting(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Product update(UUID id, UpdateProductArgument argument) {
        Product product = getExisting(id);

        product.setTitle(argument.getTitle());
        product.setPrice(argument.getPrice());
        product.setCategory(argument.getCategory());

        return repository.save(product);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
