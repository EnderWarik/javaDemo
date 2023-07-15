package com.example.demo.service.category.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchCategoryArgument {
    String title;
}
