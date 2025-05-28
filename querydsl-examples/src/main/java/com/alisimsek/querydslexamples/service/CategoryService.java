package com.alisimsek.querydslexamples.service;

import com.alisimsek.querydslexamples.dto.CategoryDto;
import com.alisimsek.querydslexamples.dto.CategorySummaryDto;
import com.alisimsek.querydslexamples.entity.Category;
import com.alisimsek.querydslexamples.repository.CategoryRepository;
import com.alisimsek.querydslexamples.repository.CustomCategoryRepository;
import com.alisimsek.querydslexamples.util.CategoryConverter;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CustomCategoryRepository customCategoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryConverter.toCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryConverter.toCategoryDto(savedCategory);
    }
    
    public List<CategoryDto> findAllCategories(Pageable pageable) {
        return categoryConverter.toCategoryDtos(categoryRepository.findAll(pageable).getContent());
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

    public Page<CategoryDto> searchCategories(String name, String description, String createdAfter, Pageable pageable) {
        BooleanBuilder builder = CategoryQueryBuilder.create(name, description, createdAfter);
        Page<Category> categoriesPage = categoryRepository.findAll(builder, pageable);
        return categoriesPage.map(categoryConverter::toCategoryDto);
    }

    public List<CategorySummaryDto> getCategoryStatistics() {
        return customCategoryRepository.getCategoryStatistics();
    }
}