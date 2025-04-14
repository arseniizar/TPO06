package com.example.tpo06.services;

import com.example.tpo06.dto.PersonDTO;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public List<PersonDTO> generatePersons(int count, String language, Set<String> additionalFields) {
        Faker faker = new Faker(Locale.forLanguageTag(language));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            persons.add(generatePerson(faker, dtf, additionalFields));
        }
        return persons;
    }

    private PersonDTO generatePerson(Faker faker, DateTimeFormatter dtf, Set<String> additionalFields) {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String dob = LocalDate.now().minusYears(faker.number().numberBetween(18, 70)).format(dtf);
        String address = generateAddress(faker, additionalFields);
        String university = generateUniversity(faker, additionalFields);
        String country = generateCountry(faker, additionalFields);
        String height = generateHeight(faker, additionalFields);
        String weight = generateWeight(faker, additionalFields);
        String hobby = generateHobby(faker, additionalFields);
        String petName = generatePetName(faker, additionalFields);
        String favouriteMusicGenre = generateFavouriteMusicGenre(faker, additionalFields);
        return new PersonDTO(firstName, lastName, dob, address, university,
                country, height, weight, hobby, petName, favouriteMusicGenre);
    }

    private String generateAddress(Faker faker, Set<String> fields) {
        return fields.contains("address") ? faker.address().fullAddress() : "";
    }

    private String generateUniversity(Faker faker, Set<String> fields) {
        return fields.contains("university") ? faker.university().name() : "";
    }

    private String generateCountry(Faker faker, Set<String> fields) {
        return fields.contains("country") ? faker.address().country() : "";
    }

    private String generateHeight(Faker faker, Set<String> fields) {
        if (!fields.contains("height")) return "";
        int height = faker.number().numberBetween(150, 200);
        return height + " cm";
    }

    private String generateWeight(Faker faker, Set<String> fields) {
        if (!fields.contains("weight")) return "";
        int weight = faker.number().numberBetween(50, 100);
        return weight + " kg";
    }

    private String generateHobby(Faker faker, Set<String> fields) {
        if (!fields.contains("hobby")) return "";
        String[] hobbies = {"Reading", "Sports", "Gaming", "Traveling", "Cooking"};
        return hobbies[faker.random().nextInt(hobbies.length)];
    }

    private String generatePetName(Faker faker, Set<String> fields) {
        return fields.contains("petName") ? faker.animal().name() : "";
    }

    private String generateFavouriteMusicGenre(Faker faker, Set<String> fields) {
        if (!fields.contains("favouriteMusicGenre")) return "";
        String[] genres = {"Rock", "Pop", "Jazz", "Classical", "Hip-Hop", "Electronic"};
        return genres[faker.random().nextInt(genres.length)];
    }
}
