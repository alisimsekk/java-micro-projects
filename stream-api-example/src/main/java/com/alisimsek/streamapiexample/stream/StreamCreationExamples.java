package com.alisimsek.streamapiexample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream Creation Examples
 */
public class StreamCreationExamples {

    public static void main(String[] args) {
        // Stream.of() - Creates a stream from specified elements
        Stream<String> streamOf = Stream.of("Java", "Stream", "API");
        System.out.println("Stream.of() example:");
        streamOf.forEach(System.out::println);

        // Arrays.stream() - Creates a stream from an array
        String[] array = {"Java", "Stream", "API"};
        Stream<String> streamFromArray = Arrays.stream(array);
        System.out.println("\nArrays.stream() example:");
        streamFromArray.forEach(System.out::println);

        // Collection.stream() - Creates a stream from a collection
        List<String> list = Arrays.asList("Java", "Stream", "API");
        Stream<String> streamFromCollection = list.stream();
        System.out.println("\nCollection.stream() example:");
        streamFromCollection.forEach(System.out::println);

        // Stream.generate() - Creates an infinite stream (limited here with limit)
        Stream<Double> streamGenerated = Stream.generate(Math::random).limit(3);
        System.out.println("\nStream.generate() example:");
        streamGenerated.forEach(System.out::println);

        // Stream.iterate() - Creates a stream with an initial value and a function
        Stream<Integer> streamIterated = Stream.iterate(1, n -> n + 2).limit(5);
        System.out.println("\nStream.iterate() example:");
        streamIterated.forEach(System.out::println);

        // IntStream, LongStream, DoubleStream - Special streams for primitive values
        IntStream intStream = IntStream.range(1, 5); // 1 to 4
        System.out.println("\nIntStream.range() example:");
        intStream.forEach(System.out::println);

        System.out.println("\nIntStream.rangeClosed() example:"); // 1 to 5
        IntStream.rangeClosed(1, 5).forEach(System.out::println);

        // Creating an empty stream
        Stream<String> emptyStream = Stream.empty();
        System.out.println("\nStream.empty() example (empty stream):");
        emptyStream.forEach(System.out::println);
    }
} 