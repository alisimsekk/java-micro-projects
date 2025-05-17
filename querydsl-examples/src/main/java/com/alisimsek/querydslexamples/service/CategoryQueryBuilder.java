package com.alisimsek.querydslexamples.service;

import com.alisimsek.querydslexamples.entity.QCategory;
import com.querydsl.core.BooleanBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CategoryQueryBuilder {
    public static BooleanBuilder create(String name, String description, String createdAfter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(name)) {
            builder.and(QCategory.category.name.containsIgnoreCase(name));
        }
        if (StringUtils.hasText(description)) {
            builder.and(QCategory.category.description.containsIgnoreCase(description));
        }
        if (Objects.nonNull(createdAfter)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDateTime formattedCreatedAfter= LocalDate.parse(createdAfter.trim(), formatter).atStartOfDay();
            log.info("formatted createdAfter date: " + formattedCreatedAfter);
            builder.and(QCategory.category.createdAt.goe(formattedCreatedAfter));
        }
        return builder;
    }
}
