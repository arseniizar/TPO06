package com.example.tpo06.data;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FormData {
    private int count = 5;
    private String language = "en";
    private Set<String> additional = new HashSet<>();
}
