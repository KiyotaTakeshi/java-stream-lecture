package com.kiyotakeshi.beans;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)
public class Student {
    private String name;
    private int score;
}
