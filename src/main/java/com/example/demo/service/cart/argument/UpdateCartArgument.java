package com.example.demo.service.cart.argument;

import com.example.demo.model.Product;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class UpdateCartArgument {

    List<Product> products;
}
