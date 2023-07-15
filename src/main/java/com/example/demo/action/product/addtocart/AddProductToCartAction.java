package com.example.demo.action.product.addtocart;

import com.example.demo.model.Cart;
import com.example.demo.model.CustomUser;
import com.example.demo.model.Product;
import com.example.demo.security.AuthService;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.cart.argument.UpdateCartArgument;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.user.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddProductToCartAction {

    private final CartService cartService;

    private final ProductService productService;

    private final AuthService authService;

    private final CustomUserService customUserService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Cart execute(AddProductToCartActionArgument argument) {
        UUID authorizedUserId = authService.getAuthorizedUserId();

        CustomUser user = customUserService.getExisting(authorizedUserId);

        Product product = productService.getExisting(argument.getProductId());

        Cart cart = user.getCart();

        List<Product> products = cart.getProducts();
        products.add(product);

        return cartService.update(cart.getId(), UpdateCartArgument.builder()
                                                                  .products(products)
                                                                  .build());

    }
}
