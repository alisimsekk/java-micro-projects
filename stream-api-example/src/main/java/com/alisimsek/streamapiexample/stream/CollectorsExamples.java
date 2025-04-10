package com.alisimsek.streamapiexample.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Stream API - Collectors Class Examples
 */
public class CollectorsExamples {

    public static void main(String[] args) {
        // Creating sample data
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", "IT", 8000, "Istanbul"),
                new Employee("Mehmet", "HR", 6000, "Ankara"),
                new Employee("Ayşe", "IT", 9000, "Istanbul"),
                new Employee("Zeynep", "Finance", 7000, "Izmir"),
                new Employee("Fatma", "HR", 6500, "Ankara"),
                new Employee("Ahmet", "IT", 7500, "Istanbul"),
                new Employee("Mustafa", "Finance", 8500, "Izmir"),
                new Employee("Elif", "IT", 8200, "Ankara")
        );

        // 1. toList() - Converting Stream to List
        System.out.println("--- toList() example ---");
        List<String> names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("Names: " + names);

        // 2. toSet() - Converting Stream to Set (removes duplicate elements)
        System.out.println("\n--- toSet() example ---");
        Set<String> departments = employees.stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
        System.out.println("Departments: " + departments);

        // 3. toMap() - Converting Stream to Map
        System.out.println("\n--- toMap() example ---");
        Map<String, Double> nameSalaryMap = employees.stream()
                .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println("Name-Salary Map:");
        nameSalaryMap.forEach((name, salary) -> System.out.println(name + ": " + salary));

        // 4. toMap() - With merge function for duplicate keys
        System.out.println("\n--- toMap() with duplicate keys ---");
        Map<String, Double> deptMaxSalaryMap = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Employee::getSalary,
                        (salary1, salary2) -> Math.max(salary1, salary2)
                ));
        System.out.println("Highest Salary by Department:");
        deptMaxSalaryMap.forEach((dept, maxSalary) -> System.out.println(dept + ": " + maxSalary));
        
        // 5. toCollection() - Converting to a specific collection type
        System.out.println("\n--- toCollection() example ---");
        LinkedList<String> nameLinkedList = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Names (LinkedList): " + nameLinkedList);
        
        // 6. joining() - String joining
        System.out.println("\n--- joining() example ---");
        String allNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", ", "Employees: ", "."));
        System.out.println(allNames);
        
        // 7. counting() - Counting elements
        System.out.println("\n--- counting() example ---");
        long count = employees.stream()
                .collect(Collectors.counting());
        System.out.println("Total number of employees: " + count);
        
        // 8. summingDouble() - Calculating sum
        System.out.println("\n--- summingDouble() example ---");
        double totalSalary = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("Total salary: " + totalSalary);
        
        // 9. averagingDouble() - Calculating average
        System.out.println("\n--- averagingDouble() example ---");
        double averageSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("Average salary: " + averageSalary);
        
        // 10. summarizingDouble() - Statistics
        System.out.println("\n--- summarizingDouble() example ---");
        DoubleSummaryStatistics salaryStats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Salary statistics: " + salaryStats);
        
        // 11. groupingBy() - Grouping
        System.out.println("\n--- groupingBy() example ---");
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        
        employeesByDept.forEach((dept, empList) -> {
            System.out.println("\n" + dept + " department employees:");
            empList.forEach(emp -> System.out.println("  - " + emp.getName() + " (" + emp.getSalary() + ")"));
        });
        
        // 12. groupingBy() - Two-level grouping
        System.out.println("\n--- groupingBy() two-level grouping example ---");
        Map<String, Map<String, List<Employee>>> employeesByDeptAndCity = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.groupingBy(Employee::getCity)
                ));
        
        employeesByDeptAndCity.forEach((dept, cityMap) -> {
            System.out.println("\n" + dept + " department employees (by city):");
            cityMap.forEach((city, empList) -> {
                System.out.println("  " + city + ":");
                empList.forEach(emp -> System.out.println("    - " + emp.getName() + " (" + emp.getSalary() + ")"));
            });
        });
        
        // 13. partitioningBy() - Partitioning by boolean condition
        System.out.println("\n--- partitioningBy() example ---");
        Map<Boolean, List<Employee>> employeesBySalary = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 7000));
        
        System.out.println("\nHigh-salary employees (>7000):");
        employeesBySalary.get(true).forEach(emp -> 
                System.out.println("  - " + emp.getName() + " (" + emp.getSalary() + ")"));
        
        System.out.println("\nLow-salary employees (<=7000):");
        employeesBySalary.get(false).forEach(emp -> 
                System.out.println("  - " + emp.getName() + " (" + emp.getSalary() + ")"));
        
        // 14. mapping() - Transformation after grouping
        System.out.println("\n--- mapping() example ---");
        Map<String, Set<String>> deptEmployeeNames = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toSet())
                ));
        
        System.out.println("Employee names by department:");
        deptEmployeeNames.forEach((dept, nameSet) -> 
                System.out.println("  " + dept + ": " + nameSet));
        
        // 15. reducing() - Reduction after grouping
        System.out.println("\n--- reducing() example ---");
        Map<String, Optional<Employee>> highestPaidByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.reducing((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2)
                ));
        
        System.out.println("Highest paid employees by department:");
        highestPaidByDept.forEach((dept, empOpt) -> 
                empOpt.ifPresent(emp -> System.out.println("  " + dept + ": " + emp.getName() + " (" + emp.getSalary() + ")")));
        
        // 16. collectingAndThen() - Post-collection transformation
        System.out.println("\n--- collectingAndThen() example ---");
        List<String> sortedNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.sort(list);
                            return list;
                        }
                ));
        
        System.out.println("Sorted names: " + sortedNames);
        
        // 17. teeing() - Combining two collectors
        System.out.println("\n--- teeing() example (Java 12+) ---");
        record SalaryStats(double average, double total) {}
        
        SalaryStats stats = employees.stream()
                .collect(Collectors.teeing(
                        Collectors.averagingDouble(Employee::getSalary),
                        Collectors.summingDouble(Employee::getSalary),
                        SalaryStats::new
                ));
        
        System.out.println("Salary statistics - Average: " + stats.average() + ", Total: " + stats.total());
        
        // 18. filtering() - Pre-collection filtering
        System.out.println("\n--- filtering() example (Java 9+) ---");
        Map<String, List<Employee>> highPaidByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.filtering(e -> e.getSalary() > 7000, Collectors.toList())
                ));
        
        System.out.println("High-salary employees by department (>7000):");
        highPaidByDept.forEach((dept, empList) -> {
            System.out.println("  " + dept + ":");
            if (empList.isEmpty()) {
                System.out.println("    No high-salary employees");
            } else {
                empList.forEach(emp -> System.out.println("    - " + emp.getName() + " (" + emp.getSalary() + ")"));
            }
        });
    }

    // Example class
    static class Employee {
        private String name;
        private String department;
        private double salary;
        private String city;

        public Employee(String name, String department, double salary, String city) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.city = city;
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

        public String getCity() {
            return city;
        }
    }
} 