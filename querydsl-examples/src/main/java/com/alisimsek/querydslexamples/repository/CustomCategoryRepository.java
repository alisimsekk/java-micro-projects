package com.alisimsek.querydslexamples.repository;

import com.alisimsek.querydslexamples.dto.CategorySummaryDto;
import com.alisimsek.querydslexamples.entity.QCategory;
import com.alisimsek.querydslexamples.entity.QProduct;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomCategoryRepository {

    private final JPAQueryFactory queryFactory;

    /**
     * Aggregation functions for category statistics
     */
    public List<CategorySummaryDto> getCategoryStatistics() {
        QProduct product = QProduct.product;
        QCategory category = QCategory.category;

        return queryFactory
                .select(Projections.constructor(CategorySummaryDto.class,
                        category.id,
                        category.name,
                        category.description,
                        product.count(),
                        product.price.min(),
                        product.price.max(),
                        product.price.avg(),
                        product.stock.sum()
                        ))
                .from(category)
                .leftJoin(product).on(product.category.eq(category))
                .groupBy(category.id, category.name, category.description)
                .orderBy(product.count().desc())
                .fetch();
    }
}
