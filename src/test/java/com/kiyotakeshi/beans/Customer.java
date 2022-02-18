package com.kiyotakeshi.beans;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class Customer {
    private final String name;
    private final String phoneNumber;
}
