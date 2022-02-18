package com.kiyotakeshi;

import com.kiyotakeshi.beans.Course;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OperationForCourses {

    List<Course> courses = List.of(
            new Course("spring", "java", 68, 220),
            new Course("spring boot", "java", 70, 430),
            new Course("ktor", "kotlin", 85, 10),
            new Course("aws", "public cloud", 90, 430),
            new Course("gcp", "public cloud", 92, 50),
            new Course("azure", "public cloud", 81, 80),
            new Course("terraform", "IaC", 79, 250),
            new Course("microservices", "Architecture", 98, 35),
            new Course("ansible", "IaC", 58, 13)
    );

    @Test
    void match() {
        Predicate<Course> reviewScoreGreaterThan90Predicate = c -> c.getReviewScore() > 90;

        System.out.println(
                courses.stream()
                        // .allMatch(reviewScoreGreaterThan90Predicate)
                        .allMatch(c -> c.getReviewScore() > 90)
        ); // false

        System.out.println(
                courses.stream()
                        .anyMatch(reviewScoreGreaterThan90Predicate)
        ); // true
    }

    @Test
    void compare() {
        Comparator<Course> comparingByNumberOfStudents = Comparator.comparing(Course::getNumberOfStudents).reversed();

        Comparator<Course> comparingByNumberOfStudentsAndReviewScore =
                Comparator.comparing(Course::getNumberOfStudents).thenComparing(Course::getReviewScore).reversed();

        System.out.println(
                courses.stream()
                        .sorted(comparingByNumberOfStudents)
                        .collect(Collectors.toList())
        );

        System.out.println(
                courses.stream()
                        .sorted(comparingByNumberOfStudentsAndReviewScore)
                        .collect(Collectors.toList())
        );
    }

    @Test
    void minFindFirst() {
        Course course = courses.stream()
                // .filter(c -> c.getReviewScore() > 90)
                .filter(c -> c.getReviewScore() > 99)
                // .min(Comparator.comparing(Course::getNumberOfStudents))
                .findFirst()
                .orElse(new Course(
                        "spring boot",
                        "java",
                        70,
                        430)
                );
        System.out.println(course);
    }

    @Test
    void sumCountAverageMax() {
        System.out.println(
                courses.stream()
                        .filter(c -> c.getNumberOfStudents() > 100)
                        .mapToInt(Course::getNumberOfStudents)
                        // .sum()
                        // .count()
                        // .average()
                        .max()
                        .getAsInt()
        );
    }

    @Test
    void collect() {
        Map<String, List<Course>> groupByCategory = courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory));

        System.out.println(groupByCategory);

        Map<String, Long> groupByCategoryAndCount = courses.stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting()));

        System.out.println(groupByCategoryAndCount);

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(
                                Course::getCategory, Collectors.mapping(Course::getName, Collectors.toList())
                        ))
        );
    }

    @Test
    void collect2() {
        System.out.println(
                courses.stream()
                        .map(Course::getName)
                        .collect(Collectors.joining(","))
        );
    }

    @Test
    void concat() {
        List<Course> courses2 = List.of(
                new Course("react", "javascript", 88, 440),
                new Course("vue", "javascript", 68, 220),
                new Course("flask", "python", 82, 150),
                new Course("django", "python", 66, 380)
        );

        System.out.println(
                Stream.concat(courses.stream(), courses2.stream()).collect(Collectors.toList())
        );
    }

    @Test
    void intermediateOperationIsLazy() {
        System.out.println(
                courses.stream()
                        .map(Course::getName)
                        .peek(System.out::println)
                        .filter(c -> c.length() > 11)
                        .map(String::toUpperCase)
                        .peek(System.out::println)
                        .findFirst()
                        .orElse("no course over 11 characters")
        );
    }

    @Test
    void replaceAll() {
        List<String> modifyCourses = courses.stream().map(Course::getName).collect(Collectors.toList());
        System.out.println(modifyCourses);

        modifyCourses.replaceAll(String::toUpperCase);
        System.out.println(modifyCourses);
    }

    @Test
    void removeIf() {
        List<String> modifyCourses = courses.stream().map(Course::getName).collect(Collectors.toList());

        modifyCourses.removeIf(c -> c.length() < 8);
        System.out.println(modifyCourses);
    }
}
