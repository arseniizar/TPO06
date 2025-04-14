package com.example.tpo06.controllers;

import com.example.tpo06.dto.PersonDTO;
import com.example.tpo06.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

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
        return switch (language) {
            case "es" -> new String[]{
                    "Nombre", "Apellido", "Fecha de Nacimiento",
                    "Dirección", "Universidad", "País",
                    "Altura", "Peso", "Pasatiempo", "Nombre de Mascota", "Género Musical Favorito"
            };
            case "fr" -> new String[]{
                    "Prénom", "Nom", "Date de Naissance",
                    "Adresse", "Université", "Pays",
                    "Taille", "Poids", "Loisir", "Nom de l'animal", "Genre Musical Préféré"
            };
            case "de" -> new String[]{
                    "Vorname", "Nachname", "Geburtsdatum",
                    "Adresse", "Universität", "Land",
                    "Größe", "Gewicht", "Hobby", "Haustiername", "Lieblingsmusikgenre"
            };
            case "it" -> new String[]{
                    "Nome", "Cognome", "Data di Nascita",
                    "Indirizzo", "Università", "Paese",
                    "Altezza", "Peso", "Hobby", "Nome dell'Animale Domestico", "Genere Musicale Preferito"
            };
            case "pt" -> new String[]{
                    "Primeiro Nome", "Sobrenome", "Data de Nascimento",
                    "Endereço", "Universidade", "País",
                    "Altura", "Peso", "Hobby", "Nome do Animal de Estimação", "Gênero Musical Favorito"
            };
            case "uk" -> new String[]{
                    "Ім'я", "Прізвище", "Дата народження",
                    "Адреса", "Університет", "Країна",
                    "Зріст", "Вага", "Хобі", "Ім'я улюбленця", "Улюблений музичний жанр"
            };
            case "zh" -> new String[]{
                    "名字", "姓氏", "出生日期",
                    "地址", "大学", "国家",
                    "身高", "体重", "爱好", "宠物名称", "最爱的音乐类型"
            };
            case "ja" -> new String[]{
                    "名", "姓", "生年月日",
                    "住所", "大学", "国",
                    "身長", "体重", "趣味", "ペットの名前", "好きな音楽のジャンル"
            };
            case "ar" -> new String[]{
                    "الاسم الأول", "اسم العائلة", "تاريخ الميلاد",
                    "العنوان", "الجامعة", "البلد",
                    "الطول", "الوزن", "الهواية", "اسم الحيوان الأليف", "نوع الموسيقى المفضل"
            };
            default -> new String[]{
                    "First Name", "Last Name", "Date of Birth",
                    "Address", "University", "Country",
                    "Height", "Weight", "Hobby", "Pet Name", "Favourite Music Genre"
            };
        };
    }
}
