package com.example.demo.controller.category.mapper;

import com.example.demo.controller.category.dto.CategoryDto;
import com.example.demo.controller.category.dto.CreateCategoryDto;
import com.example.demo.controller.category.dto.SearchCategoryDto;
import com.example.demo.model.Category;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    SearchCategoryArgument toSearchArgument(SearchCategoryDto dto);

    CategoryDto toDto(Category category);

    CreateCategoryArgument toCreateArgument(CreateCategoryDto dto);
}
