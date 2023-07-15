package com.example.demo.controller.cart;

import com.example.demo.action.product.addtocart.AddProductToCartAction;
import com.example.demo.controller.cart.dto.CartDto;
import com.example.demo.model.Cart;
import com.example.demo.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.controller.cart.mapper.CartMapper.CART_MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController {

    private final AddProductToCartAction addProductToCartAction;

    private final CartService cartService;

    @PostMapping("add/{productId}")
    public CartDto addProductToCart(@PathVariable UUID productId) {
        Cart cart = addProductToCartAction.execute(CART_MAPPER.toAddProductToCartActionArgument(productId));
        return CART_MAPPER.toDto(cart);
    }

    @GetMapping("list")
    public List<CartDto> list() {
        return cartService.list()
                          .stream()
                          .map(CART_MAPPER::toDto)
                          .collect(Collectors.toList());
    }
}
