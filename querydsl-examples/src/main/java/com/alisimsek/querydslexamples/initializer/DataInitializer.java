package com.alisimsek.querydslexamples.initializer;

import com.alisimsek.querydslexamples.entity.Category;
import com.alisimsek.querydslexamples.entity.Product;
import com.alisimsek.querydslexamples.repository.CategoryRepository;
import com.alisimsek.querydslexamples.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(CategoryRepository categoryRepo, ProductRepository productRepo) {
        return args -> {
            // Category 1
            Category electronics = Category.builder()
                    .name("Electronics")
                    .description("Electronic devices and gadgets")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryRepo.save(electronics);

            // Category 2
            Category books = Category.builder()
                    .name("Books")
                    .description("All kinds of books")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryRepo.save(books);

            // Category 3
            Category home = Category.builder()
                    .name("Home")
                    .description("Home appliances and furniture")
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            categoryRepo.save(home);

            Product smartphone = Product.builder()
                    .name("Smartphone")
                    .description("Latest model smartphone")
                    .price(new BigDecimal("699.99"))
                    .stock(50)
                    .category(electronics)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Product laptop = Product.builder()
                    .name("Laptop")
                    .description("Lightweight and powerful")
                    .price(new BigDecimal("999.99"))
                    .stock(30)
                    .category(electronics)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Product springBook = Product.builder()
                    .name("Spring in Action")
                    .description("Spring Framework book")
                    .price(new BigDecimal("49.99"))
                    .stock(200)
                    .category(books)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Product hibernateBook = Product.builder()
                    .name("Hibernate Tips")
                    .description("Hibernate ORM book")
                    .price(new BigDecimal("39.99"))
                    .stock(150)
                    .category(books)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            Product vacuum = Product.builder()
                    .name("Vacuum Cleaner")
                    .description("Powerful vacuum cleaner")
                    .price(new BigDecimal("199.99"))
                    .stock(30)
                    .category(home)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            productRepo.saveAll(Arrays.asList(smartphone, laptop, springBook, hibernateBook, vacuum));
        };
    }
}
