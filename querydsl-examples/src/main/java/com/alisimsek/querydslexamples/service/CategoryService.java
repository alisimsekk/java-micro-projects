package com.alisimsek.querydslexamples.service;

import com.alisimsek.querydslexamples.dto.CategoryDto;
import com.alisimsek.querydslexamples.entity.Category;
import com.alisimsek.querydslexamples.repository.CategoryRepository;
import com.alisimsek.querydslexamples.util.CategoryConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryConverter.toCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.toCategoryDto(savedCategory);
    }
    
    public List<CategoryDto> findAllCategories() {
        return categoryConverter.toCategoryDtos(categoryRepository.findAll());
    }
    
    public CategoryDto findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryConverter::toCategoryDto).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        
        existingCategory.setName(categoryDto.getName());
        existingCategory.setDescription(categoryDto.getDescription());
        
        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryConverter.toCategoryDto(updatedCategory);
    }
    
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
} 