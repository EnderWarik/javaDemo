package com.example.demo.service.user.argument;

import com.example.demo.model.Cart;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCustomUserArgument {
    String email;
    String password;
    Cart cart;
}
