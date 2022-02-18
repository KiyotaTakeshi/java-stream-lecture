package com.kiyotakeshi;

import com.kiyotakeshi.beans.Person;
import com.kiyotakeshi.mock.SampleData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstStep {

    @Test
    @DisplayName("命令的(手続き的)な書き方")
    void imperativeApproach() throws IOException {
        // set up(arrange)
        // 1. Find people aged less or equal 20
        // 2. Limit 10 people
        List<Person> persons = SampleData.getPersons();
        List<Person> youngPeople = new ArrayList<>();
        int limit = 5;
        int counter = 0;
        for (Person person : persons) {
            if (person.getAge() <= 20) {
                youngPeople.add(person);
                counter++;
                if (counter == limit) {
                    break;
                }
            }
        }

        // exercise(act)
        youngPeople.forEach(System.out::println);
    }

    @Test
    @DisplayName("宣言的な書き方")
    void declarativeApproach() throws IOException {

    }
}
