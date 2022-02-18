package com.kiyotakeshi;

import com.kiyotakeshi.beans.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.*;

public class FunctionalInterfaceSample {

    @Test
    void function() {
        System.out.println("Function takes 1 argument and produces 1 result");
        // int increment = increment(0);

        Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

        int increment = incrementByOneFunction.apply(1);
        System.out.println(increment);
    }

    @Test
    void biFunction() {
        System.out.println("BiFunction takes 2 argument and produces 1 result");

        BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
                (numberToIncrementByOne, numberToMultiplyBy) ->
                        (numberToIncrementByOne + 1) * numberToMultiplyBy;

        System.out.println(incrementByOneAndMultiplyBiFunction.apply(2, 5));
    }

    @Test
    void consumer() {
        Customer mike = new Customer("mike", "0120-444-444");

        // single input argument and return no result
        // @see https://docs.oracle.com/javase/jp/8/docs/api/java/util/function/Consumer.html
        Consumer<Customer> greetCustomerConsumer = customer ->
                System.out.println("Hello " + customer.getName()
                        + ", send your phone: " + customer.getPhoneNumber());

        greetCustomerConsumer.accept(mike);
    }

    @Test
    void biConsumer() {
        Customer mike = new Customer("mike", "0120-444-444");

        // two argument and return no result
        BiConsumer<Customer, Boolean> greetCustomerBiConsumer = (customer, showPhoneNumber) ->
                System.out.println("Hello " + customer.getName()
                        + ", send your phone: "
                        + (showPhoneNumber ? customer.getPhoneNumber() : "****-***-***"));

        greetCustomerBiConsumer.accept(mike, false);
    }

    @Test
    void predicate() {
        // represents a predicate of one argument
        // @see https://docs.oracle.com/javase/jp/8/docs/api/java/util/function/Predicate.html
        Predicate<String> isPhoneNumberValidPredicate = phoneNumber ->
                phoneNumber.startsWith("0120") && phoneNumber.length() == 12;

        System.out.println(isPhoneNumberValidPredicate.test("0120-444-444"));
        System.out.println(isPhoneNumberValidPredicate.test("090-xxx-xxx"));
    }

    @Test
    void predicate2() {
        Predicate<String> isPhoneNumberValidPredicate = phoneNumber ->
                phoneNumber.startsWith("0120") && phoneNumber.length() == 12;

        Predicate<String> containersNumber4 = phoneNumber -> phoneNumber.contains("4");

        System.out.println("Is phone number valid and contains number 4 = "
                + isPhoneNumberValidPredicate.and(containersNumber4).test("0120-444-444"));

        System.out.println("Is phone number valid or contains number 4 = "
                + isPhoneNumberValidPredicate.or(containersNumber4).test("0120-333-333"));
    }

    @Test
    void supplier() {
        // represents a supplier of results
        // @see https://docs.oracle.com/javase/jp/8/docs/api/java/util/function/Supplier.html
        Supplier<String> getDBConnectionUrl = () -> "jdbc://localhost:5432/sample" ;

        System.out.println(getDBConnectionUrl.get());
    }

    @Test
    void supplier2() {
        Supplier<List<String>> getDBConnectionUrlSupplier = () ->
                List.of(
                        "jdbc://localhost:5432/sample",
                        "jdbc://localhost:5432/playground"
                );

        System.out.println(getDBConnectionUrlSupplier.get());
    }
}
