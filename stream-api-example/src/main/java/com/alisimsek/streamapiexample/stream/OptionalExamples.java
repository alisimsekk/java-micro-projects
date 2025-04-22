package com.alisimsek.streamapiexample.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Optional Examples (related to Stream API)
 */
public class OptionalExamples {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Mehmet", "Ali", "Ayşe", "Zeynep");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> emptyList = Arrays.asList();

        // findFirst() - Returns the first element of the Stream as an Optional
        System.out.println("findFirst() example:");
        Optional<String> firstNameOptional = names.stream().findFirst();
        System.out.println("Is the first name present? " + firstNameOptional.isPresent());
        System.out.println("First name: " + firstNameOptional.orElse("No name"));

        // findAny() - Returns any element from the Stream as an Optional
        System.out.println("\nfindAny() example:");
        Optional<String> anyNameOptional = names.stream().findAny();
        System.out.println("Any name: " + anyNameOptional.orElse("No name"));

        // findFirst() with Empty Stream
        System.out.println("\nfindFirst() with Empty Stream example:");
        Optional<Integer> emptyFirstOptional = emptyList.stream().findFirst();
        System.out.println("Is the first element from empty stream present? " + emptyFirstOptional.isPresent());
        System.out.println("First element from empty stream: " + emptyFirstOptional.orElse(0));

        // max() and min() methods also return Optional
        System.out.println("\nmax() and min() examples (return Optional):");
        Optional<Integer> maxOptional = numbers.stream().max(Integer::compareTo);
        Optional<Integer> minOptional = numbers.stream().min(Integer::compareTo);
        
        System.out.println("Maximum number: " + maxOptional.orElse(0));
        System.out.println("Minimum number: " + minOptional.orElse(0));

        // reduce() returns Optional in some cases
        System.out.println("\nreduce() example (returns Optional):");
        Optional<Integer> sumOptional = numbers.stream().reduce(Integer::sum);
        System.out.println("Sum: " + sumOptional.orElse(0));

        // Optional methods
        System.out.println("\nOptional methods:");
        
        // 1. isPresent() - Checks if a value exists
        System.out.println("isPresent() example:");
        Optional<String> optName = Optional.of("Ali");
        System.out.println("Is value present? " + optName.isPresent());

        // 2. ifPresent() - Performs an action if a value exists
        System.out.println("\nifPresent() example:");
        optName.ifPresent(name -> System.out.println("Hello " + name));

        // 3. orElse() - Returns the value or a default if none exists
        System.out.println("\norElse() example:");
        String name = optName.orElse("Guest");
        System.out.println("Name: " + name);

        // 4. orElseGet() - Returns the value or calls a supplier function if none exists
        System.out.println("\norElseGet() example:");
        String nameFromSupplier = optName.orElseGet(() -> "Default Name");
        System.out.println("Name: " + nameFromSupplier);

        // 5. orElseThrow() - Returns the value or throws an exception if none exists
        System.out.println("\norElseThrow() example:");
        try {
            String nameOrThrow = Optional.<String>empty().orElseThrow(() -> new RuntimeException("Name not found"));
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // 6. map() - Transforms the value inside the Optional
        System.out.println("\nmap() example:");
        Optional<Integer> nameLength = optName.map(String::length);
        System.out.println("Name length: " + nameLength.orElse(0));

        // 7. filter() - Keeps the value if it matches the condition, returns empty Optional otherwise
        System.out.println("\nfilter() example:");
        Optional<String> filteredName = optName.filter(n -> n.length() > 5);
        System.out.println("Filtered name: " + filteredName.orElse("Name too short"));

        // Example of Optional usage with Stream API
        System.out.println("\nOptional usage with Stream API:");
        
        String result = names.stream()
                .filter(n -> n.startsWith("Z"))
                .findFirst()
                .map(n -> n.toUpperCase())
                .orElse("No name starting with that letter");
        
        System.out.println("First name starting with Z (uppercase): " + result);
        
        // Optional.empty(), Optional.of() and Optional.ofNullable() examples
        System.out.println("\nOptional creation methods:");
        
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("empty(): " + emptyOptional.orElse("Empty"));
        
        Optional<String> ofOptional = Optional.of("Value");
        System.out.println("of(): " + ofOptional.orElse("Empty"));
        
        String nullableValue = null;
        Optional<String> ofNullableOptional = Optional.ofNullable(nullableValue);
        System.out.println("ofNullable() with null value: " + ofNullableOptional.orElse("Empty"));
    }
} 