package com.alisimsek.streamapiexample.stream;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream API - Real World Application Examples
 */
public class RealWorldExamples {

    public static void main(String[] args) {
        System.out.println("=== STREAM API REAL WORLD EXAMPLES ===");
        
        // Example 1: Online order processing
        orderProcessingExample();
        
        // Example 2: File operations
        fileProcessingExample();
        
        // Example 3: Data analytics
        dataAnalyticsExample();
        
        // Example 4: Performance optimization with parallel processing
        parallelProcessingExample();
        
        // Example 5: Data filtering and transformation
        dataTransformationExample();
    }
    
    /**
     * Example 1: Online order processing
     */
    private static void orderProcessingExample() {
        System.out.println("\n--- Online Order Processing Example ---");
        
        List<Order> orders = Arrays.asList(
                new Order(1L, "Laptop", 8500.0, OrderStatus.DELIVERED, LocalDate.of(2023, Month.APRIL, 5)),
                new Order(2L, "Phone", 4500.0, OrderStatus.PROCESSING, LocalDate.of(2023, Month.MAY, 12)),
                new Order(3L, "Tablet", 3200.0, OrderStatus.DELIVERED, LocalDate.of(2023, Month.MARCH, 20)),
                new Order(4L, "Headphones", 450.0, OrderStatus.CANCELLED, LocalDate.of(2023, Month.APRIL, 18)),
                new Order(5L, "Monitor", 2800.0, OrderStatus.DELIVERED, LocalDate.of(2023, Month.MAY, 3)),
                new Order(6L, "Keyboard", 350.0, OrderStatus.PROCESSING, LocalDate.of(2023, Month.MAY, 15)),
                new Order(7L, "Mouse", 180.0, OrderStatus.DELIVERED, LocalDate.of(2023, Month.FEBRUARY, 27)),
                new Order(8L, "Printer", 1200.0, OrderStatus.CANCELLED, LocalDate.of(2023, Month.APRIL, 22))
        );
        
        // 1. Total amount of delivered orders
        double totalDeliveredAmount = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(Order::getAmount)
                .sum();
        
        System.out.println("Total amount of delivered orders: " + totalDeliveredAmount + " TL");
        
        // 2. Order distribution by month
        Map<Month, List<Order>> ordersByMonth = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getOrderDate().getMonth()));
        
        System.out.println("\nOrder distribution by month:");
        ordersByMonth.forEach((month, orderList) -> {
            System.out.println("  " + month + ": " + orderList.size() + " orders, " + 
                    orderList.stream().mapToDouble(Order::getAmount).sum() + " TL");
        });
        
        // 3. Average amounts by order status
        Map<OrderStatus, Double> avgAmountByStatus = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getStatus,
                        Collectors.averagingDouble(Order::getAmount)
                ));
        
        System.out.println("\nAverage amounts by order status:");
        avgAmountByStatus.forEach((status, avg) -> {
            System.out.println("  " + status + ": " + avg + " TL");
        });
        
        // 4. Highest amount order
        Optional<Order> highestOrder = orders.stream()
                .max(Comparator.comparing(Order::getAmount));
        
        highestOrder.ifPresent(order -> {
            System.out.println("\nHighest amount order: " + 
                    order.getProductName() + " - " + order.getAmount() + " TL");
        });
        
        // 5. List and summary of cancelled orders
        List<Order> cancelledOrders = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.CANCELLED)
                .collect(Collectors.toList());
        
        double totalCancelledAmount = cancelledOrders.stream()
                .mapToDouble(Order::getAmount)
                .sum();
        
        System.out.println("\nCancelled orders (" + cancelledOrders.size() + 
                " items, total " + totalCancelledAmount + " TL):");
        
        cancelledOrders.forEach(order -> {
            System.out.println("  ID: " + order.getId() + ", Product: " + order.getProductName() + 
                    ", Amount: " + order.getAmount() + " TL, Date: " + order.getOrderDate());
        });
    }
    
    /**
     * Example 2: File operations
     */
    private static void fileProcessingExample() {
        System.out.println("\n--- File Operations Example ---");
        
        System.out.println("Note: This example is simulated as it requires access to a real file path.");
        
        // Example of a real file operation (simulation)
        List<String> lines = Arrays.asList(
                "2023-05-10,user1,LOGIN",
                "2023-05-10,user2,LOGIN",
                "2023-05-10,user1,LOGOUT",
                "2023-05-11,user3,LOGIN",
                "2023-05-11,user2,LOGOUT",
                "2023-05-12,user3,LOGOUT",
                "2023-05-12,user4,LOGIN",
                "2023-05-12,user4,LOGOUT"
        );
        
        System.out.println("Users who logged in from the log file:");
        
        // Finding unique users who logged in
        Set<String> uniqueUsers = lines.stream()
                .filter(line -> line.contains("LOGIN"))
                .map(line -> line.split(",")[1])
                .collect(Collectors.toSet());
        
        uniqueUsers.forEach(user -> System.out.println("  " + user));
        
        // Activity count by day
        Map<String, Long> activityCountByDay = lines.stream()
                .collect(Collectors.groupingBy(
                        line -> line.split(",")[0],
                        Collectors.counting()
                ));
        
        System.out.println("\nActivity count by day:");
        activityCountByDay.forEach((day, count) -> {
            System.out.println("  " + day + ": " + count + " activities");
        });
        
        // Grouping by log type
        Map<String, List<String>> logsByType = lines.stream()
                .collect(Collectors.groupingBy(
                        line -> line.split(",")[2],
                        Collectors.mapping(
                                line -> line.split(",")[0] + " - " + line.split(",")[1], 
                                Collectors.toList()
                        )
                ));
        
        System.out.println("\nLogs by type:");
        logsByType.forEach((type, logs) -> {
            System.out.println("  " + type + ":");
            logs.forEach(log -> System.out.println("    " + log));
        });
    }
    
    /**
     * Example 3: Data analytics
     */
    private static void dataAnalyticsExample() {
        System.out.println("\n--- Data Analytics Example ---");
        
        List<SalesRecord> salesData = Arrays.asList(
                new SalesRecord("Electronics", "Laptop", 2023, Month.JANUARY, 15, 12500.0),
                new SalesRecord("Electronics", "Phone", 2023, Month.JANUARY, 25, 8500.0),
                new SalesRecord("Electronics", "Tablet", 2023, Month.FEBRUARY, 10, 6200.0),
                new SalesRecord("Home Appliances", "Refrigerator", 2023, Month.JANUARY, 5, 9500.0),
                new SalesRecord("Home Appliances", "Washing Machine", 2023, Month.FEBRUARY, 8, 7800.0),
                new SalesRecord("Furniture", "Sofa Set", 2023, Month.MARCH, 3, 18500.0),
                new SalesRecord("Furniture", "Dining Table", 2023, Month.JANUARY, 7, 5400.0),
                new SalesRecord("Electronics", "Laptop", 2023, Month.MARCH, 12, 13000.0),
                new SalesRecord("Electronics", "Phone", 2023, Month.FEBRUARY, 30, 9000.0),
                new SalesRecord("Home Appliances", "Oven", 2023, Month.MARCH, 6, 4200.0)
        );
        
        // 1. Total sales by category
        Map<String, Double> totalSalesByCategory = salesData.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getCategory,
                        Collectors.summingDouble(record -> record.getUnitsSold() * record.getUnitPrice())
                ));
        
        System.out.println("Total sales by category:");
        totalSalesByCategory.forEach((category, total) -> {
            System.out.println("  " + category + ": " + total + " TL");
        });
        
        // 2. Total units sold by month
        Map<Month, Integer> totalUnitsByMonth = salesData.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getMonth,
                        Collectors.summingInt(SalesRecord::getUnitsSold)
                ));
        
        System.out.println("\nTotal units sold by month:");
        totalUnitsByMonth.forEach((month, units) -> {
            System.out.println("  " + month + ": " + units + " units");
        });
        
        // 3. Top selling product
        Optional<SalesRecord> topSellingProduct = salesData.stream()
                .collect(Collectors.maxBy(Comparator.comparing(SalesRecord::getUnitsSold)));
        
        topSellingProduct.ifPresent(record -> {
            System.out.println("\nTop selling product: " + record.getProduct() + " (" + 
                    record.getUnitsSold() + " units)");
        });
        
        // 4. Total revenue by product
        Map<String, Double> revenueByProduct = salesData.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getProduct,
                        Collectors.summingDouble(record -> record.getUnitsSold() * record.getUnitPrice())
                ));
        
        System.out.println("\nTotal revenue by product:");
        revenueByProduct.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " TL");
                });
        
        // 5. Sales analysis by category and month
        Map<String, Map<Month, Double>> salesByCategoryAndMonth = salesData.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getCategory,
                        Collectors.groupingBy(
                                SalesRecord::getMonth,
                                Collectors.summingDouble(record -> record.getUnitsSold() * record.getUnitPrice())
                        )
                ));
        
        System.out.println("\nSales revenue by category and month:");
        salesByCategoryAndMonth.forEach((category, monthMap) -> {
            System.out.println("  " + category + ":");
            monthMap.forEach((month, revenue) -> {
                System.out.println("    " + month + ": " + revenue + " TL");
            });
        });
    }
    
    /**
     * Example 4: Performance optimization with parallel processing
     */
    private static void parallelProcessingExample() {
        System.out.println("\n--- Parallel Processing Example ---");
        
        // Creating large dataset (simulation)
        int dataSize = 10_000_000;
        System.out.println("Data size: " + dataSize + " elements");
        
        // Sequential processing time
        long startTime = System.currentTimeMillis();
        
        double sumSequential = Stream.iterate(1, n -> n + 1)
                .limit(dataSize)
                .mapToDouble(n -> Math.sqrt(n) * Math.log(n))
                .sum();
        
        long endTime = System.currentTimeMillis();
        long sequentialTime = endTime - startTime;
        
        System.out.println("Sequential processing result: " + sumSequential);
        System.out.println("Sequential processing time: " + sequentialTime + " ms");
        
        // Parallel processing time
        startTime = System.currentTimeMillis();
        
        double sumParallel = Stream.iterate(1, n -> n + 1)
                .limit(dataSize)
                .parallel()
                .mapToDouble(n -> Math.sqrt(n) * Math.log(n))
                .sum();
        
        endTime = System.currentTimeMillis();
        long parallelTime = endTime - startTime;
        
        System.out.println("Parallel processing result: " + sumParallel);
        System.out.println("Parallel processing time: " + parallelTime + " ms");
        System.out.println("Speedup: " + ((double) sequentialTime / parallelTime) + "x");
        
        // Practical tips
        System.out.println("\nParallel Stream Usage Tips:");
        System.out.println("1. Use for large datasets (millions of elements)");
        System.out.println("2. Use with data structures that support random access like arrays or ArrayList");
        System.out.println("3. Use when your operations involve intensive CPU calculations");
        System.out.println("4. Avoid using for order-dependent operations");
        System.out.println("5. Always perform performance tests, parallel streams aren't always faster");
    }
    
    /**
     * Example 5: Data filtering and transformation
     */
    private static void dataTransformationExample() {
        System.out.println("\n--- Data Filtering and Transformation Example ---");
        
        List<User> users = Arrays.asList(
                new User(1L, "ali.yilmaz", "Ali Yilmaz", "ali@example.com", 28, true, Arrays.asList("sports", "music", "technology")),
                new User(2L, "ayse.demir", "Ayse Demir", "ayse@example.com", 34, true, Arrays.asList("books", "travel", "music")),
                new User(3L, "mehmet.kaya", "Mehmet Kaya", "mehmet@example.com", 45, false, Arrays.asList("fishing", "gardening", "nature")),
                new User(4L, "zeynep.celik", "Zeynep Celik", "zeynep@example.com", 22, true, Arrays.asList("fashion", "photography", "music")),
                new User(5L, "can.arslan", "Can Arslan", "can@example.com", 31, true, Arrays.asList("sports", "technology", "gaming")),
                new User(6L, "selin.yildiz", "Selin Yildiz", "selin@example.com", 27, false, Arrays.asList("painting", "music", "dance"))
        );
        
        // 1. Filtering active users
        System.out.println("Active users:");
        List<User> activeUsers = users.stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
        
        activeUsers.forEach(user -> {
            System.out.println("  " + user.getName() + " (" + user.getUsername() + ")");
        });
        
        // 2. DTO (Data Transfer Object) transformation
        System.out.println("\nUser DTOs:");
        List<UserDTO> userDTOs = users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getAge()
                ))
                .collect(Collectors.toList());
        
        userDTOs.forEach(dto -> {
            System.out.println("  ID: " + dto.getId() + ", Name: " + dto.getName() + 
                    ", Email: " + dto.getEmail() + ", Age: " + dto.getAge());
        });
        
        // 3. Finding users by interest
        System.out.println("\nUsers interested in music:");
        List<User> musicLovers = users.stream()
                .filter(user -> user.getInterests().contains("music"))
                .collect(Collectors.toList());
        
        musicLovers.forEach(user -> {
            System.out.println("  " + user.getName() + " - Interests: " + user.getInterests());
        });
        
        // 4. Sorting and grouping by age
        System.out.println("\nUsers by age group:");
        Map<String, List<User>> usersByAgeGroup = users.stream()
                .collect(Collectors.groupingBy(user -> {
                    if (user.getAge() < 30) return "20-29 years";
                    else if (user.getAge() < 40) return "30-39 years";
                    else return "40+ years";
                }));
        
        usersByAgeGroup.forEach((ageGroup, userList) -> {
            System.out.println("  " + ageGroup + ":");
            userList.forEach(user -> {
                System.out.println("    " + user.getName() + " (" + user.getAge() + ")");
            });
        });
        
        // 5. List of all unique interests
        System.out.println("\nAll unique interests:");
        Set<String> allInterests = users.stream()
                .flatMap(user -> user.getInterests().stream())
                .collect(Collectors.toSet());
        
        System.out.println("  " + allInterests);
        
        // 6. User count by interest
        System.out.println("\nUser count by interest:");
        Map<String, Long> userCountByInterest = users.stream()
                .flatMap(user -> user.getInterests().stream()
                        .map(interest -> new AbstractMap.SimpleEntry<>(interest, user)))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.counting()
                ));
        
        userCountByInterest.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> {
                    System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " users");
                });
    }
    
    // Inner classes and enums
    
    enum OrderStatus {
        PROCESSING, DELIVERED, CANCELLED
    }
    
    static class Order {
        private Long id;
        private String productName;
        private double amount;
        private OrderStatus status;
        private LocalDate orderDate;
        
        public Order(Long id, String productName, double amount, OrderStatus status, LocalDate orderDate) {
            this.id = id;
            this.productName = productName;
            this.amount = amount;
            this.status = status;
            this.orderDate = orderDate;
        }
        
        public Long getId() { return id; }
        public String getProductName() { return productName; }
        public double getAmount() { return amount; }
        public OrderStatus getStatus() { return status; }
        public LocalDate getOrderDate() { return orderDate; }
    }
    
    static class SalesRecord {
        private String category;
        private String product;
        private int year;
        private Month month;
        private int unitsSold;
        private double unitPrice;
        
        public SalesRecord(String category, String product, int year, Month month, int unitsSold, double unitPrice) {
            this.category = category;
            this.product = product;
            this.year = year;
            this.month = month;
            this.unitsSold = unitsSold;
            this.unitPrice = unitPrice;
        }
        
        public String getCategory() { return category; }
        public String getProduct() { return product; }
        public int getYear() { return year; }
        public Month getMonth() { return month; }
        public int getUnitsSold() { return unitsSold; }
        public double getUnitPrice() { return unitPrice; }
    }
    
    static class User {
        private Long id;
        private String username;
        private String name;
        private String email;
        private int age;
        private boolean active;
        private List<String> interests;
        
        public User(Long id, String username, String name, String email, int age, boolean active, List<String> interests) {
            this.id = id;
            this.username = username;
            this.name = name;
            this.email = email;
            this.age = age;
            this.active = active;
            this.interests = interests;
        }
        
        public Long getId() { return id; }
        public String getUsername() { return username; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public int getAge() { return age; }
        public boolean isActive() { return active; }
        public List<String> getInterests() { return interests; }
    }
    
    static class UserDTO {
        private Long id;
        private String name;
        private String email;
        private int age;
        
        public UserDTO(Long id, String name, String email, int age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }
        
        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public int getAge() { return age; }
    }
} 