package com.example.demo.action.user.create;

import com.example.demo.model.Cart;
import com.example.demo.model.CustomUser;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.cart.argument.CreateCartArgument;
import com.example.demo.service.user.CustomUserService;
import com.example.demo.service.user.argument.CreateCustomUserArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateCustomUserAction {

    private final CustomUserService customUserService;

    private final CartService cartService;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public CustomUser execute(CreateCustomUserActionArgument argument) {
        Cart cart = cartService.create(CreateCartArgument.builder().build());

        return customUserService.create(CreateCustomUserArgument.builder()
                                                                .email(argument.getEmail())
                                                                .password(argument.getPassword())
                                                                .cart(cart)
                                                                .build());
    }
}
