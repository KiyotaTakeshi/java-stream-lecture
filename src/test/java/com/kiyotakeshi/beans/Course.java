package com.kiyotakeshi.beans;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class Course {
    @ToString.Include(rank = 100)
    private String name;

    @ToString.Exclude
    private String category;

    @ToString.Include(rank = 98)
    private int reviewScore;

    @ToString.Include(rank = 99)
    private int numberOfStudents;
}
