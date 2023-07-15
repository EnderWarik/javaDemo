package com.example.demo.service.cart;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.cart.argument.CreateCartArgument;
import com.example.demo.service.cart.argument.UpdateCartArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Cart create(CreateCartArgument argument) {
        return repository.save(Cart.builder()
                                   .products(argument.getProducts())
                                   .build());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Cart update(UUID id, UpdateCartArgument argument) {
        Cart cart = getExisting(id);
        cart.setProducts(argument.getProducts());
        return repository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getExisting(UUID uuid) {
        return repository.findById(uuid).orElseThrow(RuntimeException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> list() {
        return repository.findAll();
    }

}
