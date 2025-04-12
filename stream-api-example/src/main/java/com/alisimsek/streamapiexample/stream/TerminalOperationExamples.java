package com.alisimsek.streamapiexample.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream Terminal Operation Examples
 */
public class TerminalOperationExamples {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ali", "Mehmet", "Ayşe", "Zeynep", "Ali", "Fatma", "Ahmet");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", "IT", 8000),
                new Employee("Mehmet", "HR", 6000),
                new Employee("Ayşe", "IT", 9000),
                new Employee("Zeynep", "Finance", 7000),
                new Employee("Ahmet", "IT", 7500)
        );

        // forEach() - Performs an action for each element
        System.out.println("forEach() example:");
        names.stream().forEach(System.out::println);

        // toArray() - Converts stream to an array
        System.out.println("\ntoArray() example:");
        String[] nameArray = names.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(nameArray));

        // collect() - Converts stream to a collection
        System.out.println("\ncollect() example (List):");
        List<String> nameList = names.stream().collect(Collectors.toList());
        System.out.println(nameList);

        System.out.println("\ncollect() example (Set - without duplicates):");
        Set<String> nameSet = names.stream().collect(Collectors.toSet());
        System.out.println(nameSet);

        // reduce() - Reduces elements to a single value
        System.out.println("\nreduce() example (sum):");
        Optional<Integer> sum = numbers.stream().reduce(Integer::sum);
        sum.ifPresent(s -> System.out.println("Sum: " + s));

        System.out.println("\nreduce() example (with initial value):");
        int sumWithInitial = numbers.stream().reduce(100, Integer::sum);
        System.out.println("100 + all numbers = " + sumWithInitial);

        // count() - Returns the number of elements
        System.out.println("\ncount() example:");
        long count = names.stream().count();
        System.out.println("Number of names: " + count);

        // min() and max() - Returns the minimum and maximum elements
        System.out.println("\nmin() example:");
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        min.ifPresent(m -> System.out.println("Minimum number: " + m));

        System.out.println("\nmax() example:");
        Optional<Integer> max = numbers.stream().max(Integer::compareTo);
        max.ifPresent(m -> System.out.println("Maximum number: " + m));

        // anyMatch(), allMatch(), noneMatch() - Match checks
        System.out.println("\nanyMatch() example:");
        boolean anyMatch = numbers.stream().anyMatch(n -> n > 5);
        System.out.println("Is there any number greater than 5? " + anyMatch);

        System.out.println("\nallMatch() example:");
        boolean allMatch = numbers.stream().allMatch(n -> n > 0);
        System.out.println("Are all numbers greater than 0? " + allMatch);

        System.out.println("\nnoneMatch() example:");
        boolean noneMatch = numbers.stream().noneMatch(n -> n < 0);
        System.out.println("Are no numbers less than 0? " + noneMatch);

        // findFirst() and findAny() - Finding elements
        System.out.println("\nfindFirst() example:");
        Optional<String> first = names.stream().findFirst();
        first.ifPresent(f -> System.out.println("First name: " + f));

        System.out.println("\nfindAny() example:");
        Optional<String> any = names.stream().findAny();
        any.ifPresent(a -> System.out.println("Any name: " + a));

        // collect() with grouping and joining
        System.out.println("\ncollect() - Grouping by example (grouping by department):");
        Map<String, List<Employee>> byDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        byDepartment.forEach((dept, empList) -> {
            System.out.println(dept + " department employees:");
            empList.forEach(emp -> System.out.println("  - " + emp.getName() + " (Salary: " + emp.getSalary() + ")"));
        });

        // collect() with statistics
        System.out.println("\ncollect() - Statistics calculation example:");
        DoubleSummaryStatistics salaryStats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Salary statistics: " + salaryStats);

        // collect() with string joining
        System.out.println("\ncollect() - String joining example:");
        String namesCombined = names.stream()
                .distinct()
                .collect(Collectors.joining(", ", "Names: ", "."));
        System.out.println(namesCombined);
    }

    // Example class
    static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }
    }
} 