package com.example.demo.controller.cart.mapper;

import com.example.demo.action.product.addtocart.AddProductToCartActionArgument;
import com.example.demo.controller.cart.dto.CartDto;
import com.example.demo.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface CartMapper {

    CartMapper CART_MAPPER = Mappers.getMapper(CartMapper.class);

    AddProductToCartActionArgument toAddProductToCartActionArgument(UUID productId);

    CartDto toDto(Cart cart);
}
