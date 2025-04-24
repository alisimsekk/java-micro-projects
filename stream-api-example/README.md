# Java Stream API Examples

A comprehensive collection of examples demonstrating the power and flexibility of Java Stream API.

## Overview

This project provides practical examples of Java Stream API usage in various scenarios. It's designed to help developers understand how to effectively use Stream API for data processing, transformation, and analysis in Java applications.

## Features

- **Basic Stream Operations**: Examples of filtering, mapping, and reducing operations
- **Intermediate Operations**: Demonstrations of operations like `map`, `filter`, `sorted`, `distinct`, etc.
- **Terminal Operations**: Examples of terminal operations like `collect`, `forEach`, `count`, etc.
- **Collectors**: Comprehensive examples of the `Collectors` utility class
- **Real-World Applications**: Practical examples showing Stream API in business scenarios

## Examples Included

### Basic Stream Operations
- Creating streams from collections
- Filtering elements
- Mapping elements
- Reducing streams to a single value

### Intermediate Operations
- `map`: Transforming elements
- `filter`: Filtering elements based on conditions
- `sorted`: Sorting elements
- `distinct`: Removing duplicates
- `limit`: Limiting the number of elements
- `skip`: Skipping elements
- `flatMap`: Flattening nested structures

### Terminal Operations
- `collect`: Collecting elements into collections
- `forEach`: Performing actions on each element
- `count`: Counting elements
- `min`/`max`: Finding minimum/maximum elements
- `anyMatch`/`allMatch`/`noneMatch`: Checking conditions
- `findFirst`/`findAny`: Finding elements

### Collectors Examples
- Converting streams to collections (`toList`, `toSet`, `toMap`)
- Joining strings
- Calculating statistics
- Grouping elements
- Partitioning elements
- Custom collectors

### Real-World Examples
- Online order processing
- File operations
- Data analytics
- Performance optimization with parallel processing
- Data filtering and transformation

## Getting Started

### Running the Examples
Each example class contains a `main` method that can be executed to see the Stream API in action.

## Project Structure

```
stream-api-example/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── alisimsek/
│                   └── streamapiexample/
│                       ├── StreamApiExampleApplication.java
│                       └── stream/
│                           ├── CollectorsExamples.java
│                           ├── IntermediateOperationExamples.java
│                           ├── OptionalExamples.java
│                           ├── ParallelStreamExamples.java
│                           ├── RealWorldExamples.java
│                           ├── SpecializedStreamExamples.java
│                           ├── StreamCreationExamples.java
│                           └── TerminalOperationExamples.java
└── pom.xml
```

## Notes

1. **Use Method References**: When possible, use method references instead of lambda expressions for better readability.
   ```java
   // Instead of
   .map(item -> item.getName())
   
   // Use
   .map(Item::getName)
   ```

2. **Chain Operations**: Stream operations are designed to be chained. Use this feature to create clear, readable data processing pipelines.

3. **Parallel Streams**: Use parallel streams for large datasets and CPU-intensive operations, but always measure performance first.

4. **Collectors**: Use the appropriate collector for your needs. The `Collectors` utility class provides many useful collectors.