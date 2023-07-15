package com.example.demo.action.product.addtocart;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class AddProductToCartActionArgument {

    UUID productId;
}
