package com.example.demo.service.user.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateCustomUserArgument {
    String email;
    Long balance;
    String password;
}
