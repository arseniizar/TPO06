package com.example.tpo06.controllers;

import com.example.tpo06.data.FormData;
import com.example.tpo06.dto.PersonDTO;
import com.example.tpo06.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("formData")
public class PersonController {

    private final DataService dataService;

    public PersonController(DataService dataService) {
        this.dataService = dataService;
    }

    @ModelAttribute("formData")
    public FormData formData() {
        return new FormData();
    }

    @GetMapping
    public String index(Model model, @ModelAttribute("formData") FormData formData) {
        model.addAttribute("languages", getLanguages());
        return "index";
    }

    @PostMapping("generate")
    public String generateData(@ModelAttribute("formData") FormData formData,
                               RedirectAttributes redirectAttributes) {
        try {
            List<PersonDTO> persons = dataService.generatePersons(
                    formData.getCount(), formData.getLanguage(), formData.getAdditional());
            redirectAttributes.addFlashAttribute("persons", persons);
            redirectAttributes.addFlashAttribute("headers", getHeaders(formData.getLanguage()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error generating data: " + e.getMessage());
        }
        redirectAttributes.addFlashAttribute("languages", getLanguages());
        return "redirect:/";
    }

    private String[] getLanguages() {
        return new String[]{"en", "es", "fr", "de", "it", "pt", "uk", "zh", "ja", "ar"};
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
