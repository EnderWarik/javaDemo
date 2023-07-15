package com.example.demo.controller.category;

import com.example.demo.controller.category.dto.CategoryDto;
import com.example.demo.controller.category.dto.CreateCategoryDto;
import com.example.demo.controller.category.dto.SearchCategoryDto;
import com.example.demo.model.Category;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.controller.category.mapper.CategoryMapper.CATEGORY_MAPPER;

@RestController
@RequiredArgsConstructor
@RequestMapping("category")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping("list")
    public List<CategoryDto> list(SearchCategoryDto dto) {
        SearchCategoryArgument argument = CATEGORY_MAPPER.toSearchArgument(dto);
        return categoryService.getList(argument)
                              .stream()
                              .map(CATEGORY_MAPPER::toDto)
                              .collect(Collectors.toList());
    }

    @PostMapping("create")
    public CategoryDto create(@RequestBody CreateCategoryDto dto) {
        CreateCategoryArgument argument = CATEGORY_MAPPER.toCreateArgument(dto);

        Category category = categoryService.create(argument);
        return CATEGORY_MAPPER.toDto(category);
    }

}
