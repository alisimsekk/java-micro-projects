package com.alisimsek.querydslexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for product summary information with price classification
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSummaryDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String categoryName;
    private String priceCategory; // "Budget", "Mid-range", "Premium", "Luxury"
    private Integer stock;
    private Boolean isLowStock; // Flag indicating if stock is below threshold
} 