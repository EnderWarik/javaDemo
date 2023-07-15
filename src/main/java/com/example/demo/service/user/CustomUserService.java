package com.example.demo.service.user;

import com.example.demo.model.CustomUser;
import com.example.demo.service.user.argument.CreateCustomUserArgument;
import com.example.demo.service.user.argument.UpdateCustomUserArgument;

import java.util.UUID;

public interface CustomUserService {

    CustomUser create(CreateCustomUserArgument argument);

    CustomUser update(UpdateCustomUserArgument argument);

    CustomUser getExisting(UUID id);

    CustomUser findByEmail(String email);
}
