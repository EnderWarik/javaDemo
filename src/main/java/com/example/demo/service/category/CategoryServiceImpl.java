package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import com.example.demo.service.category.argument.UpdateCategoryArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;


    @Override
    public List<Category> getList(SearchCategoryArgument argument) {
        return repository.findAll();
    }

    @Override
    public Category getExisting(UUID categoryId) {
        return repository.findById(categoryId).orElseThrow(RuntimeException::new);
    }

    @Override
    public Category create(CreateCategoryArgument argument) {
        return repository.save(Category.builder()
                                       .title(argument.getTitle())
                                       .products(new ArrayList<>())
                                       .build());
    }

    @Override
    public Category update(UpdateCategoryArgument argument) {
        return repository.save(Category.builder()
                                       .title(argument.getTitle())
                                       .products(argument.getProducts())
                                       .build());
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
