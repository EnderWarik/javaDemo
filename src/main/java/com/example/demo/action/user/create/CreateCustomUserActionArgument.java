package com.example.demo.action.user.create;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCustomUserActionArgument {

    String email;

    String password;
}
