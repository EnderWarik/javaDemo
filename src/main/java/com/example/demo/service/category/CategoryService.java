package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import com.example.demo.service.category.argument.UpdateCategoryArgument;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getList(SearchCategoryArgument argument);

    Category getExisting(UUID categoryId);

    Category create(CreateCategoryArgument argument);

    Category update(UpdateCategoryArgument argument);

    void delete(UUID id);
}
