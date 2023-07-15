package com.example.demo.action.product.addtocart;

import com.example.demo.model.Cart;
import com.example.demo.model.CustomUser;
import com.example.demo.model.Product;
import com.example.demo.security.AuthService;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.cart.argument.UpdateCartArgument;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.user.CustomUserService;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

class AddProductToCartActionTest {

    private final CartService cartService = Mockito.mock(CartService.class);

    private final ProductService productService = Mockito.mock(ProductService.class);

    private final AuthService authService = Mockito.mock(AuthService.class);

    private final CustomUserService customUserService = Mockito.mock(CustomUserService.class);

    private final AddProductToCartAction action = new AddProductToCartAction(cartService,
                                                                             productService,
                                                                             authService,
                                                                             customUserService);

    @Test
    void execute() {
        // Arrange
        UUID productId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID cartId = UUID.randomUUID();

        CustomUser user = Mockito.mock(CustomUser.class);
        Product product = Mockito.mock(Product.class);
        Cart returnedCart = Mockito.mock(Cart.class);

        AddProductToCartActionArgument argument = AddProductToCartActionArgument.builder()
                                                                                .productId(productId)
                                                                                .build();
        Cart cart = Cart.builder()
                        .id(cartId)
                        .products(new ArrayList<>())
                        .build();

        Mockito.when(authService.getAuthorizedUserId()).thenReturn(userId);
        Mockito.when(customUserService.getExisting(any())).thenReturn(user);
        Mockito.when(productService.getExisting(any())).thenReturn(product);
        Mockito.when(user.getCart()).thenReturn(cart);
        Mockito.when(cartService.update(any(UUID.class), any(UpdateCartArgument.class))).thenReturn(returnedCart);

        // Act
        Cart execute = action.execute(argument);

        // Assert
        ArgumentCaptor<UUID> userIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> productIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> cartIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UpdateCartArgument> updateCartArgumentArgumentCaptor = ArgumentCaptor.forClass(UpdateCartArgument.class);

        Mockito.verify(customUserService, Mockito.only()).getExisting(userIdCaptor.capture());
        Mockito.verify(productService, Mockito.only()).getExisting(productIdCaptor.capture());
        Mockito.verify(cartService, Mockito.only()).update(cartIdCaptor.capture(), updateCartArgumentArgumentCaptor.capture());

        UpdateCartArgument expectedUpdateArgument = UpdateCartArgument.builder()
                                                                      .products(Lists.newArrayList(product))
                                                                      .build();

        Assertions.assertThat(userIdCaptor.getValue()).isEqualTo(userId);
        Assertions.assertThat(productIdCaptor.getValue()).isEqualTo(productId);
        Assertions.assertThat(cartIdCaptor.getValue()).isEqualTo(cartId);
        Assertions.assertThat(updateCartArgumentArgumentCaptor.getValue())
                  .usingRecursiveComparison()
                  .withStrictTypeChecking()
                  .isEqualTo(expectedUpdateArgument);

        Assertions.assertThat(execute).isEqualTo(returnedCart);
    }
}