package com.alisimsek.streamapiexample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Parallel Stream Examples
 */
public class ParallelStreamExamples {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        // Sequential stream
        System.out.println("Sequential Stream example:");
        long startTime = System.nanoTime();
        
        int sumSequential = numbers.stream()
                .map(ParallelStreamExamples::heavyOperation)
                .reduce(0, Integer::sum);
        
        long endTime = System.nanoTime();
        long sequentialTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        
        System.out.println("Sequential stream sum: " + sumSequential);
        System.out.println("Sequential stream time: " + sequentialTime + " ms");

        // Parallel stream
        System.out.println("\nParallel Stream example:");
        startTime = System.nanoTime();
        
        int sumParallel = numbers.parallelStream()
                .map(ParallelStreamExamples::heavyOperation)
                .reduce(0, Integer::sum);
        
        endTime = System.nanoTime();
        long parallelTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        
        System.out.println("Parallel stream sum: " + sumParallel);
        System.out.println("Parallel stream time: " + parallelTime + " ms");
        System.out.println("Speedup: " + ((double) sequentialTime / parallelTime) + "x");

        // Converting sequential stream to parallel stream using parallel() method
        System.out.println("\nparallel() method example:");
        int sumParallel2 = numbers.stream()
                .parallel()
                .map(ParallelStreamExamples::heavyOperation)
                .reduce(0, Integer::sum);
        
        System.out.println("Sum with parallel conversion: " + sumParallel2);

        // Creating parallel stream with IntStream.range
        System.out.println("\nIntStream.range().parallel() example:");
        int sum = IntStream.range(1, 21)
                .parallel()
                .map(ParallelStreamExamples::heavyOperation)
                .sum();
        
        System.out.println("IntStream parallel sum: " + sum);

        // Things to watch out for - Order-dependent operations
        System.out.println("\nParallel stream order example:");
        System.out.println("Sequential stream:");
        numbers.stream()
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\nParallel stream (UNORDERED):");
        numbers.parallelStream()
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\n\nParallel stream with forEachOrdered (ORDERED):");
        numbers.parallelStream()
                .forEachOrdered(n -> System.out.print(n + " "));

        // Performance measurement - comparing small and large data sets
        System.out.println("\n\nPerformance comparison for small data set:");
        measurePerformance(100);
        
        System.out.println("\nPerformance comparison for large data set:");
        measurePerformance(10000);
    }

    // Heavy operation simulation
    private static int heavyOperation(int number) {
        try {
            // Operation simulation
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number * 2;
    }

    // Performance measurement
    private static void measurePerformance(int size) {
        int[] array = new int[size];
        Arrays.fill(array, 1);

        // Sequential
        long startSeq = System.nanoTime();
        Arrays.stream(array).sum();
        long endSeq = System.nanoTime();

        // Parallel
        long startPar = System.nanoTime();
        Arrays.stream(array).parallel().sum();
        long endPar = System.nanoTime();

        System.out.println("Data size: " + size);
        System.out.println("Sequential stream time: " + (endSeq - startSeq) / 1_000_000.0 + " ms");
        System.out.println("Parallel stream time: " + (endPar - startPar) / 1_000_000.0 + " ms");
    }
} 