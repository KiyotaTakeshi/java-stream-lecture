package com.kiyotakeshi;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Playground2 {

    List<String> course = List.of("spring", "spring boot", "kotlin", "microservices", "kubernetes", "docker", "terraform");

    List<Integer> numbers = List.of(13, 1, 8, 4, 5, 7, 23);

    private static String addCharacterLength(String s) {
        return s + ": character length is " + s.length();
    }

    @Test
    void map() {
        course.stream()
                .map(c -> c + ": character length is " + c.length()) // lambda
                // .map(Playground2::addCharacterLength) // method reference
                .forEach(System.out::println);
    }

    @Test
    void reduce() {
        Integer sum = numbers.stream()
                // .reduce(0, (a, b) -> a + b)
                // .reduce(0, Integer::sum);
                .reduce(0, (a, b) -> {
                    System.out.println(a + " " + b);
                    return a + b;
                });

        System.out.println(sum);

        /**
         jshell> List<Integer> numbers = List.of(13, 1, 8, 4, 5, 7, 23);

         jshell> numbers.stream().reduce(0, (x,y) -> x+y)
         $3 ==> 61

         jshell> numbers.stream().reduce(0, (x,y) -> x-y)
         $4 ==> -61

         jshell> numbers.stream().reduce(0, (x,y) -> x)
         $6 ==> 0

         jshell> numbers.stream().reduce(0, (x,y) -> y)
         $7 ==> 23

         jshell> numbers.stream().reduce(0, (x,y) -> x > y ? x : y)
         $8 ==> 23
         */
    }

    @Test
    void streamInside() {
        /**
         @FunctionalInterface public interface Predicate<T> {

         @FunctionalInterface public interface Function<T, R> {

         @FunctionalInterface public interface Consumer<T> {

         > Conceptually, a functional interface has exactly one abstract method.
         public @interface FunctionalInterface {}

         @see https://speakerdeck.com/shintanimoto/stream-api-ru-men-number-jjug-number-javajo?slide=37
         Stream 内の filter, map, forEach などが引数で受け付けるものは FunctionalInterface(関数型インターフェース)
         FunctionalInterface は1つしか抽象メソッドを持たないもので、lambda 内に直接記述することができる
         */
        Predicate<Integer> isEvenPredicate = n -> n % 2 == 0;

        Predicate<Integer> isEvenPredicate2 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer n) {
                return n % 2 == 0;
            }
        };

        Function<Integer, Integer> squareFunction = n -> n * n;
        Function<Integer, Integer> squareFunction2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer n) {
                return n * n;
            }
        };

        Consumer<Integer> println = System.out::println;
        Consumer<Integer> println2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer n) {
                System.out.println(n);
            }
        };

        numbers.stream()
                .filter(isEvenPredicate)
                // .filter(isEvenPredicate2)
                .map(squareFunction)
                // .map(squareFunction2)
                .forEach(println);
        // .forEach(println2);
    }

    @Test
    void steamInside2() {

        BinaryOperator<Integer> getSum = new BinaryOperator<>() {
            @Override
            public Integer apply(Integer arg1, Integer arg2) {
                return arg1 + arg2;
            }
        };

        System.out.println(
                numbers.stream()
                        // .reduce(0, (x,y) -> x + y)
                        // .reduce(0, Integer::sum)
                        .reduce(0, getSum)
        );
    }
}
