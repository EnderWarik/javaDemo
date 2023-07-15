package com.example.demo.service.cart;

import com.example.demo.model.Cart;
import com.example.demo.service.cart.argument.CreateCartArgument;
import com.example.demo.service.cart.argument.UpdateCartArgument;

import java.util.List;
import java.util.UUID;

public interface CartService {

    Cart create(CreateCartArgument argument);

    Cart update(UUID id, UpdateCartArgument argument);

    Cart getExisting(UUID uuid);

    List<Cart> list();

}
