package com.alisimsek.querydslexamples.service;

import com.alisimsek.querydslexamples.dto.CategoryDto;
import com.alisimsek.querydslexamples.dto.ProductCreateRequest;
import com.alisimsek.querydslexamples.dto.ProductDto;
import com.alisimsek.querydslexamples.dto.ProductSummaryDto;
import com.alisimsek.querydslexamples.entity.Category;
import com.alisimsek.querydslexamples.entity.Product;
import com.alisimsek.querydslexamples.repository.CategoryRepository;
import com.alisimsek.querydslexamples.repository.CustomProductRepository;
import com.alisimsek.querydslexamples.repository.ProductRepository;
import com.alisimsek.querydslexamples.util.ProductConverter;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CustomProductRepository customProductRepository;
    private final CategoryRepository categoryRepository;
    private final ProductConverter productConverter;

    public ProductDto createProduct(ProductCreateRequest request) {
        Product product = createProductEntityFromDto(request);
        Product savedProduct = productRepository.save(product);
        return productConverter.toProductDto(savedProduct);
    }

    public List<ProductDto> findAllProducts(Pageable pageable) {
        return productConverter.toProductDtos(productRepository.findAll(pageable).getContent());
    }
    
    public ProductDto findProductById(Long id) {
        return productConverter.toProductDto(productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        updateProductEntityFromDto(existingProduct, productDto);
        Product updatedProduct = productRepository.save(existingProduct);
        return productConverter.toProductDto(updatedProduct);
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private Product createProductEntityFromDto(ProductCreateRequest request) {
        Product product = productConverter.toProduct(request);
        
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));
            product.setCategory(category);
        }
        return product;
    }

    private void updateProductEntityFromDto(Product product, ProductDto dto) {
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());

        CategoryDto categoryDto = dto.getCategory();
        if (Objects.nonNull(categoryDto) && Objects.nonNull(categoryDto.getId())) {
            Category category = categoryRepository.findById(categoryDto.getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryDto.getId()));
            product.setCategory(category);
        }
    }

    public Page<ProductDto> searchProducts(String name, String categoryName, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        BooleanBuilder builder = ProductQueryBuilder.createQuery(name, categoryName, minPrice, maxPrice);
        Page<Product> productsPage = productRepository.findAll(builder, pageable);
        return productsPage.map(productConverter::toProductDto);
    }

    public List<ProductDto> findProductsWithConstructorProjection() {
        return customProductRepository.findProductsWithConstructorProjection();
    }

    public List<ProductSummaryDto> classifyProductsByPrice() {
        return customProductRepository.classifyProductsByPrice();
    }
}