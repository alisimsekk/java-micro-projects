package com.alisimsek.querydslexamples.util;

import com.alisimsek.querydslexamples.dto.CategoryDto;
import com.alisimsek.querydslexamples.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public List<CategoryDto> toCategoryDtos(List<Category> categories) {
        return categories.stream()
                .map(this::toCategoryDto)
                .collect(Collectors.toList());
    }

    public Category toCategory(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
} 