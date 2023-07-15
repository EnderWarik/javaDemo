package com.example.demo.controller.product.mapper;

import com.example.demo.action.product.create.CreateProductActionArgument;
import com.example.demo.action.product.update.UpdateProductActionArgument;
import com.example.demo.controller.product.dto.CreateProductDto;
import com.example.demo.controller.product.dto.ProductDto;
import com.example.demo.controller.product.dto.SearchProductDto;
import com.example.demo.controller.product.dto.UpdateProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.product.argument.SearchProductArgument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    SearchProductArgument toSearchArgument(SearchProductDto dto);

    ProductDto toDto(Product product);

    CreateProductActionArgument toCreateActionArgument(CreateProductDto dto);

    UpdateProductActionArgument toUpdateActionArgument(UpdateProductDto dto);
}
