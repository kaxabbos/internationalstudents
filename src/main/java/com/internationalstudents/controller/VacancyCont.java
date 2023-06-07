package com.internationalstudents.controller;

import com.internationalstudents.controller.main.Attributes;
import com.internationalstudents.model.Vacancy;
import com.internationalstudents.model.enums.VacancyStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacancy")
public class VacancyCont extends Attributes {

    @GetMapping
    public String Vacancy(Model model) {
        AddAttributesVacancy(model);
        return "vacancy";
    }

    @GetMapping("/approved/{id}")
    public String VacancyUnder_consideration(@PathVariable Long id) {
        Vacancy vacancy = vacancyRepo.getReferenceById(id);
        vacancy.setStatus(VacancyStatus.APPROVED);
        vacancyRepo.save(vacancy);
        return "redirect:/vacancy";
    }

    @GetMapping("/rejected/{id}")
    public String VacancyRejected(@PathVariable Long id) {
        Vacancy vacancy = vacancyRepo.getReferenceById(id);
        vacancy.setStatus(VacancyStatus.REJECTED);
        vacancyRepo.save(vacancy);
        return "redirect:/vacancy";
    }
}
