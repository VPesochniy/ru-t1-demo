package ru.t1.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
}
