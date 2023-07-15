package com.example.demo.service.product;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.product.argument.CreateProductArgument;
import com.example.demo.service.product.argument.SearchProductArgument;
import com.example.demo.service.product.argument.UpdateProductArgument;
import com.querydsl.core.types.Predicate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    private final ProductService service = new ProductServiceImpl(productRepository);


    @Test
    void list() {
        // Arrange
        SearchProductArgument argument = SearchProductArgument.builder()
                                                              .productName("test1")
                                                              .categoryName("test2")
                                                              .build();

        List<Product> expectedList = new ArrayList<>();
        Mockito.when(productRepository.findAll(any(Predicate.class))).thenReturn(expectedList);
        // Act
        List<Product> list = service.list(argument);
        // Assert
        ArgumentCaptor<Predicate> predicateArgumentCaptor = ArgumentCaptor.forClass(Predicate.class);
        Mockito.verify(productRepository, Mockito.only()).findAll(predicateArgumentCaptor.capture());

        Predicate capturedPredicate = predicateArgumentCaptor.getValue();


        Assertions.assertThat(capturedPredicate.toString())
                  .isEqualTo("containsIc(product.title,test1) && containsIc(product.category.title,test2)");

        Assertions.assertThat(list).usingRecursiveComparison()
                  .withStrictTypeChecking()
                  .isEqualTo(expectedList);
    }

    @Test
    void create() {
        // Arrange
        Product product = Mockito.mock(Product.class);
        CreateProductArgument argument = CreateProductArgument.builder()
                                                              .title("картофанчик")
                                                              .price(0l)
                                                              .category(Category.builder()
                                                                                .title("преколдесы")
                                                                                .build())
                                                              .build();

        Mockito.when(productRepository.save(any())).thenReturn(product);
        // Act
        Product actual = service.create(argument);
        // Assert
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        Mockito.verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();

        Product expectedCapturedProduct = Product.builder()
                                                 .title("картофанчик")
                                                 .price(0l)
                                                 .category(Category.builder()
                                                                   .title("преколдесы")
                                                                   .build())
                                                 .build();

        Assertions.assertThat(capturedProduct)
                  .usingRecursiveComparison()
                  .withStrictTypeChecking()
                  .isEqualTo(expectedCapturedProduct);

        Assertions.assertThat(actual).isEqualTo(product);
    }

    @Test
    void getExisting() {
        // Arrange
        Product product = Mockito.mock(Product.class);
        Optional<Product> optionalProduct = Optional.of(product);

        UUID id = UUID.randomUUID();

        Mockito.when(productRepository.findById(id)).thenReturn(optionalProduct);

        // Act
        Product existing = service.getExisting(id);

        // Assert
        ArgumentCaptor<UUID> idCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(productRepository).findById(idCaptor.capture());

        Assertions.assertThat(idCaptor.getValue()).isEqualTo(id);
        Assertions.assertThat(existing).isEqualTo(product);
    }

    @Test
    void update() {
        // Arrange
        Product product = Mockito.mock(Product.class);

        UUID id = UUID.randomUUID();
        Product updating = Product.builder()
                                  .id(id)
                                  .title("title")
                                  .price(0L)
                                  .category(Category.builder()
                                                    .title("uauau")
                                                    .build())
                                  .build();
        UpdateProductArgument updateProductArgument = UpdateProductArgument.builder()
                                                                           .title("test title")
                                                                           .category(Category.builder()
                                                                                             .title("test category title")
                                                                                             .build())
                                                                           .price(213L)
                                                                           .build();

        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(updating));
        Mockito.when(productRepository.save(any())).thenReturn(product);
        // Act
        Product update = service.update(id, updateProductArgument);

        // Assert
        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        Mockito.verify(productRepository).findById(uuidArgumentCaptor.capture());
        Mockito.verify(productRepository).save(productArgumentCaptor.capture());

        Product expectedProduct = Product.builder()
                                         .id(id)
                                         .title(updateProductArgument.getTitle())
                                         .price(updateProductArgument.getPrice())
                                         .category(updateProductArgument.getCategory())
                                         .build();

        Assertions.assertThat(uuidArgumentCaptor.getValue()).isEqualTo(id);
        Assertions.assertThat(productArgumentCaptor.getValue())
                  .usingRecursiveComparison()
                  .withStrictTypeChecking()
                  .isEqualTo(expectedProduct);

        Assertions.assertThat(update).isEqualTo(product);
    }

    @Test
    void delete() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        service.delete(id);

        // Assert
        ArgumentCaptor<UUID> idCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(productRepository).deleteById(idCaptor.capture());

        Assertions.assertThat(idCaptor.getValue()).isEqualTo(id);
    }
}