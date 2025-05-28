package com.alisimsek.querydslexamples.util;

import com.alisimsek.querydslexamples.dto.ProductCreateRequest;
import com.alisimsek.querydslexamples.dto.ProductDto;
import com.alisimsek.querydslexamples.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CategoryConverter categoryConverter;

    public ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(Objects.nonNull(product.getCategory()) ? categoryConverter.toCategoryDto(product.getCategory()) : null)
                .build();
    }

    public List<ProductDto> toProductDtos(List<Product> products) {
        return products.stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    public Product toProduct(ProductCreateRequest dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        return product;
    }
} 