package com.alisimsek.streamapiexample.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Specialized Stream (IntStream, LongStream, DoubleStream) Examples
 */
public class SpecializedStreamExamples {

    public static void main(String[] args) {
        // IntStream Examples
        System.out.println("--- IntStream Examples ---");
        
        // IntStream creation methods
        System.out.println("\nIntStream creation methods:");
        
        // 1. IntStream.of() method
        System.out.println("IntStream.of() example:");
        IntStream.of(1, 2, 3, 4, 5)
                .forEach(n -> System.out.print(n + " "));
        
        // 2. IntStream.range() method
        System.out.println("\n\nIntStream.range() example (1-4 range):");
        IntStream.range(1, 5) // 1, 2, 3, 4 (upper bound not inclusive)
                .forEach(n -> System.out.print(n + " "));
        
        // 3. IntStream.rangeClosed() method
        System.out.println("\n\nIntStream.rangeClosed() example (1-5 range):");
        IntStream.rangeClosed(1, 5) // 1, 2, 3, 4, 5 (upper bound inclusive)
                .forEach(n -> System.out.print(n + " "));
        
        // 4. Arrays.stream() method
        System.out.println("\n\nArrays.stream() example:");
        int[] array = {1, 2, 3, 4, 5};
        Arrays.stream(array)
                .forEach(n -> System.out.print(n + " "));
        
        // 5. Stream.mapToInt() method - Creating IntStream from regular Stream
        System.out.println("\n\nStream.mapToInt() example:");
        List<String> strings = Arrays.asList("1", "2", "3", "4", "5");
        strings.stream()
                .mapToInt(Integer::parseInt)
                .forEach(n -> System.out.print(n + " "));
        
        // IntStream methods
        System.out.println("\n\nIntStream special methods:");
        
        // Sum method
        int sum = IntStream.rangeClosed(1, 10).sum();
        System.out.println("sum() example: Sum from 1 to 10 = " + sum);
        
        // Average method
        OptionalDouble avg = IntStream.rangeClosed(1, 10).average();
        System.out.println("average() example: Average from 1 to 10 = " + avg.orElse(0));
        
        // Min and Max methods
        int min = IntStream.rangeClosed(1, 10).min().orElse(0);
        int max = IntStream.rangeClosed(1, 10).max().orElse(0);
        System.out.println("min() example: " + min);
        System.out.println("max() example: " + max);
        
        // summaryStatistics method - getting all statistics at once
        IntSummaryStatistics stats = IntStream.rangeClosed(1, 10).summaryStatistics();
        System.out.println("summaryStatistics() example: " + stats);
        System.out.println("  Sum: " + stats.getSum());
        System.out.println("  Average: " + stats.getAverage());
        System.out.println("  Min: " + stats.getMin());
        System.out.println("  Max: " + stats.getMax());
        System.out.println("  Count: " + stats.getCount());
        
        // LongStream Examples
        System.out.println("\n--- LongStream Examples ---");
        
        // LongStream creation
        System.out.println("\nLongStream creation:");
        LongStream.rangeClosed(1L, 5L)
                .forEach(n -> System.out.print(n + " "));
        
        // Working with large numbers
        System.out.println("\n\nWorking with large numbers:");
        long factorial = LongStream.rangeClosed(1L, 20L)
                .reduce(1L, (a, b) -> a * b);
        System.out.println("Factorial of 20: " + factorial);
        
        // DoubleStream Examples
        System.out.println("\n--- DoubleStream Examples ---");
        
        // DoubleStream creation
        System.out.println("\nDoubleStream creation:");
        DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5)
                .forEach(n -> System.out.print(n + " "));
        
        // Stream conversions
        System.out.println("\n\nStream conversions:");
        
        // IntStream -> Stream<Integer>
        System.out.println("IntStream -> Stream<Integer> conversion:");
        Stream<Integer> boxedStream = IntStream.rangeClosed(1, 5).boxed();
        boxedStream.forEach(n -> System.out.print(n + " "));
        
        // Stream<Integer> -> IntStream
        System.out.println("\n\nStream<Integer> -> IntStream conversion:");
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        IntStream intStream = intList.stream().mapToInt(Integer::intValue);
        intStream.forEach(n -> System.out.print(n + " "));
        
        // IntStream -> LongStream
        System.out.println("\n\nIntStream -> LongStream conversion:");
        LongStream longStream = IntStream.rangeClosed(1, 5).asLongStream();
        longStream.forEach(n -> System.out.print(n + " "));
        
        // IntStream -> DoubleStream
        System.out.println("\n\nIntStream -> DoubleStream conversion:");
        DoubleStream doubleStream = IntStream.rangeClosed(1, 5).asDoubleStream();
        doubleStream.forEach(n -> System.out.print(n + " "));
    }
} 