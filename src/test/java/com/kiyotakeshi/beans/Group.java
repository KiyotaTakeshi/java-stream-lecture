package com.kiyotakeshi.beans;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class Group {

    private List<Student> students = new ArrayList<>();

    public void add(Student student) {
        students.add(student);
    }
}
