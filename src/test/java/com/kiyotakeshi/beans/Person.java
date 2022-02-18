package com.kiyotakeshi.beans;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Integer age;
}
