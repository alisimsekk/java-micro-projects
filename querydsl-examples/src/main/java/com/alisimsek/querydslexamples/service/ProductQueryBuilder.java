package com.alisimsek.querydslexamples.service;

import com.alisimsek.querydslexamples.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductQueryBuilder {
    public static BooleanBuilder createQuery(String name, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice) {
        BooleanBuilder builder = new BooleanBuilder();
        if (name != null) {
            builder.and(QProduct.product.name.containsIgnoreCase(name));
        }
        if (categoryId != null) {
            builder.and(QProduct.product.category.id.eq(categoryId));
        }
        if (minPrice != null) {
            builder.and(QProduct.product.price.goe(minPrice));
        }
        if (maxPrice != null) {
            builder.and(QProduct.product.price.loe(maxPrice));
        }
        return builder;
    }
}
