package com.example.tpo06.services;

import com.example.tpo06.dto.PersonDTO;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;
import java.util.Set;

public class DataServiceImpl implements DataService {
    @Override
    public List<PersonDTO> generatePersons(int count, String language, Set<String> additionalFields) {
        Faker faker = new Faker(Locale.forLanguageTag(language));
        List<PersonDTO> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < count; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String dob = LocalDate.now().minusYears(faker.number().numberBetween(18, 70)).format(dtf);
            String address = additionalFields.contains("address") ? faker.address().fullAddress() : "";
            String university = additionalFields.contains("university") ? faker.university().name() : "";
            String country = additionalFields.contains("country") ? faker.address().country() : "";
            String arbitrary1 = additionalFields.contains("arbitrary1") ? faker.lorem().word() : "";
            String arbitrary2 = additionalFields.contains("arbitrary2") ? faker.lorem().word() : "";
            String arbitrary3 = additionalFields.contains("arbitrary3") ? faker.lorem().word() : "";
            String arbitrary4 = additionalFields.contains("arbitrary4") ? faker.lorem().word() : "";
            String arbitrary5 = additionalFields.contains("arbitrary5") ? faker.lorem().word() : "";
            PersonDTO person = new PersonDTO(firstName, lastName, dob, address, university,
                    country, arbitrary1, arbitrary2, arbitrary3, arbitrary4, arbitrary5);
            list.add(person);
        }
        return list;
    }
}
