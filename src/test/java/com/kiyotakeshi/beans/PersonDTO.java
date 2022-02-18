package com.kiyotakeshi.beans;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class PersonDTO {

    private Integer id;
    private String name;
    private Integer age;

    public static PersonDTO map(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getAge());
    }
}
