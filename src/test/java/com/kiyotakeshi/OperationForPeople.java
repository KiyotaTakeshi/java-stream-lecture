package com.kiyotakeshi;

import com.kiyotakeshi.beans.Person;
import com.kiyotakeshi.beans.PersonDTO;
import com.kiyotakeshi.mock.SampleData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationForPeople {

    @Test
    void filter() throws IOException {
        List<Person> persons = SampleData.getPersons();

        List<Person> females = persons.stream()
                .filter(p -> p.getGender().equals("Female"))
                // .filter(femalePredicate)
                .collect(Collectors.toList());
        females.forEach(System.out::println);
    }

    @Test
    void sorted() throws IOException {
        List<Person> persons = SampleData.getPersons();

        List<Person> sortedPersons = persons.stream()
                // .sorted(Comparator.comparing(Person::getAge))
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());
        sortedPersons.forEach(System.out::println);
    }

    @Test
    void allMatchAnyMatch() throws IOException {
        List<Person> persons = SampleData.getPersons();

        System.out.println("all match");
        boolean allMatch = persons.stream()
                .allMatch(person -> person.getAge() > 8);
        System.out.println(allMatch);

        System.out.println("-------------------");
        System.out.println("any match");
        boolean anyMatch = persons.stream()
                .anyMatch(person -> person.getAge() > 8);
        System.out.println(anyMatch);
    }

    @Test
    void maxMin() throws IOException {
        List<Person> persons = SampleData.getPersons();

        System.out.println("-------------------");
        System.out.println("max");
//        Optional<Person> max = persons.stream()
//                .max(Comparator.comparing(Person::getAge));
        persons.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        System.out.println("-------------------");
        System.out.println("min");
        persons.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
    }

    @Test
    void groupingBy() throws IOException {
        List<Person> persons = SampleData.getPersons();

        Map<String, List<Person>> personsGroupByGender = persons.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        personsGroupByGender.forEach((gender, person) -> {
            System.out.println(gender);
            person.forEach(System.out::println);
            System.out.println();
        });
    }

    @Test
    void filterAndMaxAndMap() throws IOException {
        List<Person> persons = SampleData.getPersons();
        Optional<String> oldestAgeFemaleName = persons.stream()
                .filter(person -> person.getGender().equals("Female"))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getFirstName);
        oldestAgeFemaleName.ifPresent(System.out::println);
    }

    @Test
    void map() throws IOException {
        // set up(arrange)
        List<Person> persons = SampleData.getPersons();

        List<PersonDTO> personDtos = persons.stream()
                .filter(p -> p.getAge() > 20)
                .map(p -> new PersonDTO(
                        p.getId(), p.getFirstName(), p.getAge()
                ))
                .collect(Collectors.toList());

        // exercise(act)
        personDtos.forEach(System.out::println);

        // verify(assert)
        // assertThat(personDtos.size()).isEqualTo(persons.size());
        assertThat(personDtos.size()).isLessThan(persons.size());
    }

    @Test
    void mapUsingMethodReference() throws IOException {
        // set up(arrange)
        List<Person> persons = SampleData.getPersons();

        List<PersonDTO> personDtos = persons.stream()
                .filter(p -> p.getAge() > 20)
                .map(PersonDTO::map)
                .collect(Collectors.toList());

        // exercise(act)
        personDtos.forEach(System.out::println);

        // verify(assert)
        assertThat(personDtos.size()).isLessThan(persons.size());
    }

    @Test
    void map2() throws IOException {
        // set up(arrange)
        List<Person> persons = SampleData.getPersons();

        Map<Integer, PersonDTO> personDtoMap = persons.stream()
                .filter(p -> p.getAge() > 20)
                .map(PersonDTO::map)
                .collect(Collectors.toMap(PersonDTO::getId, p -> p));

        // exercise(act)
        System.out.println(personDtoMap.get(1));
    }
}
