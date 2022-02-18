package com.kiyotakeshi;

import com.kiyotakeshi.beans.Car;
import com.kiyotakeshi.mock.SampleData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OperationForCars {

    @Test
    void filter() throws IOException {
        // set up(arrange)
        List<Car> cars = SampleData.getCars();

        // Predicate<Car> pricePredicate = car -> car.getPrice() < 15_000.00;
        // Predicate<Car> colorPredicate = car -> car.getColor().equals("Black");
        List<Car> carsLessThan15kAndBlack = cars.stream()
                .filter(c -> c.getPrice() < 15_000.00)
                .filter(c -> c.getColor().equals("Black"))
                .collect(Collectors.toList());

        // exercise(act)
        carsLessThan15kAndBlack.forEach(System.out::println);
    }
}
