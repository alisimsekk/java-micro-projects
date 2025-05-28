package com.alisimsek.querydslexamples.repository;

import com.alisimsek.querydslexamples.dto.CategoryDto;
import com.alisimsek.querydslexamples.dto.ProductDto;
import com.alisimsek.querydslexamples.dto.ProductSummaryDto;
import com.alisimsek.querydslexamples.entity.QCategory;
import com.alisimsek.querydslexamples.entity.QProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProductRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * Constructor projection example
     */
    public List<ProductDto> findProductsWithConstructorProjection() {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        return queryFactory
                .select(Projections.constructor(ProductDto.class,
                        product.id,
                        product.name,
                        product.description,
                        product.price,
                        product.stock,
                        Projections.constructor(CategoryDto.class,
                                category.id,
                                category.name,
                                category.description
                        )))
                .from(product)
                .join(product.category, category)
                .fetch();
    }

    /**
     * Case expressions for price classification
     */
    public List<ProductSummaryDto> classifyProductsByPrice() {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        return queryFactory
                .select(Projections.constructor(ProductSummaryDto.class,
                        product.id,
                        product.name,
                        product.price,
                        category.name,
                        new CaseBuilder()
                                .when(product.price.lt(BigDecimal.valueOf(50))).then("Budget")
                                .when(product.price.lt(BigDecimal.valueOf(200))).then("Mid-range")
                                .when(product.price.lt(BigDecimal.valueOf(500))).then("Premium")
                                .otherwise("Luxury"),
                        product.stock,
                        product.stock.lt(10)))
                .from(product)
                .join(product.category, category)
                //.orderBy(product.price.asc())
                .fetch();
    }
} 