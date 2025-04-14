package com.example.tpo06.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String university;
    private String country;
    private String height;
    private String weight;
    private String hobby;
    private String petName;
    private String favouriteMusicGenre;
}
