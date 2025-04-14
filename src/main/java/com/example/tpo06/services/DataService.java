package com.example.tpo06.services;

import com.example.tpo06.dto.PersonDTO;

import java.util.List;
import java.util.Set;

public interface DataService {
    List<PersonDTO> generatePersons(int count, String language, Set<String> additionalFields);
}
