package com.alisimsek.streamapiexample.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Stream Intermediate Operation Examples
 */
public class IntermediateOperationExamples {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mehmet", "Ayşe", "Zeynep", "Ali", "Fatma", "Ahmet");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // filter() - Filters elements that match the condition
        System.out.println("filter() example (numbers greater than 5):");
        numbers.stream()
                .filter(n -> n > 5)
                .forEach(System.out::println);

        // map() - Transforms each element by applying a function
        System.out.println("\nmap() example (squares of numbers):");
        numbers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);

        // flatMap() - Flattens nested streams
        System.out.println("\nflatMap() example (flattening nested lists):");
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        nestedLists.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        // distinct() - Removes duplicate elements
        System.out.println("\ndistinct() example (unique names):");
        names.stream()
                .distinct()
                .forEach(System.out::println);

        // sorted() - Sorts elements
        System.out.println("\nsorted() example (alphabetical order):");
        names.stream()
                .sorted()
                .forEach(System.out::println);

        // sorted() with Comparator - Custom sorting
        System.out.println("\nsorted() example (sorting by length):");
        names.stream()
                .sorted(Comparator.comparingInt(String::length))
                .forEach(System.out::println);

        // peek() - Used for debugging during intermediate operations
        System.out.println("\npeek() example (viewing elements during processing):");
        numbers.stream()
                .peek(n -> System.out.print("Processing: " + n + " -> "))
                .map(n -> n * 2)
                .forEach(System.out::println);

        // limit() - Takes the first n elements
        System.out.println("\nlimit() example (first 3 elements):");
        names.stream()
                .limit(3)
                .forEach(System.out::println);

        // skip() - Skips the first n elements
        System.out.println("\nskip() example (skip first 3 elements):");
        names.stream()
                .skip(3)
                .forEach(System.out::println);

        // takeWhile() - Takes elements as long as the condition is true
        System.out.println("\ntakeWhile() example (take numbers less than 5):");
        numbers.stream()
                .takeWhile(n -> n < 5)
                .forEach(System.out::println);

        // dropWhile() - Drops elements as long as the condition is true
        System.out.println("\ndropWhile() example (drop numbers less than 5):");
        numbers.stream()
                .dropWhile(n -> n < 5)
                .forEach(System.out::println);
    }
}