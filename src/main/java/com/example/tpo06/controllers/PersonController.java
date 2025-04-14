package com.example.tpo06.controllers;

import com.example.tpo06.dto.PersonDTO;
import com.example.tpo06.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PersonController {

    private final DataService dataService;

    public PersonController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("languages", getLanguages());
        model.addAttribute("selectedCount", 5);
        model.addAttribute("selectedLanguage", "en");
        model.addAttribute("selectedAdditional", new HashSet<String>());
        return "index";
    }

    @PostMapping("/generate")
    public String generateData(@RequestParam("count") int count,
                               @RequestParam("language") String language,
                               @RequestParam(value = "additional", required = false) List<String> additional,
                               Model model) {
        Set<String> additionalFields = convertToSet(additional);
        try {
            List<PersonDTO> persons = dataService.generatePersons(count, language, additionalFields);
            model.addAttribute("persons", persons);
            model.addAttribute("headers", getHeaders(language));
        } catch (Exception e) {
            model.addAttribute("error", "Error generating data: " + e.getMessage());
        }
        model.addAttribute("languages", getLanguages());
        model.addAttribute("selectedCount", count);
        model.addAttribute("selectedLanguage", language);
        model.addAttribute("selectedAdditional", additionalFields);
        return "index";
    }

    private String[] getLanguages() {
        return new String[]{"en", "es", "fr", "de", "it", "pt", "uk", "zh", "ja", "ar"};
    }

    private Set<String> convertToSet(List<String> additional) {
        return additional != null ? new HashSet<>(additional) : new HashSet<>();
    }

    private String[] getHeaders(String language) {
        try (InputStream in = getClass().getResourceAsStream("/headers.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            String prefix = language + "=";
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(prefix)) {
                    String headersPart = line.substring(prefix.length());
                    String[] headers = headersPart.split(",");
                    for (int i = 0; i < headers.length; i++) {
                        headers[i] = headers[i].trim();
                    }
                    return headers;
                }
            }
        } catch (IOException | NullPointerException e) {
            System.err.println("Error loading headers for language " + language + ": " + e.getMessage());
        }
        return new String[]{
                "First Name", "Last Name", "Date of Birth",
                "Address", "University", "Country",
                "Height", "Weight", "Hobby", "Pet Name", "Favourite Music Genre"
        };
    }
}
