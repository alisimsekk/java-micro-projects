package com.alisimsek.querydslexamples.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * DTO for returning category statistics and summary information
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorySummaryDto {
    private Long id;
    private String name;
    private String description;
    private Long productCount;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Double avgPrice;
    private Integer totalStock;
}