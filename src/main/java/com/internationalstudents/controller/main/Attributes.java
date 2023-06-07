package com.internationalstudents.controller.main;

import com.internationalstudents.model.Score;
import com.internationalstudents.model.Vacancy;
import com.internationalstudents.model.enums.*;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("yesnos", YesNo.values());
        model.addAttribute("citizenships", Citizenship.values());
        model.addAttribute("maritals", Marital.values());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAllByOrderByRole());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesHuman(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesVacancy(Model model) {
        AddAttributes(model);
        List<Vacancy> vacancies = vacancyRepo.findAllByStatus(VacancyStatus.WAITING);
        vacancies.addAll(vacancyRepo.findAllByStatus(VacancyStatus.APPROVED));
        vacancies.addAll(vacancyRepo.findAllByStatus(VacancyStatus.REJECTED));
        model.addAttribute("vacancies", vacancies);
    }

    protected void AddAttributesEnrollments(Model model) {
        AddAttributes(model);
        model.addAttribute("enrollments", usersRepo.findAllByRole(Role.USER));
    }

    protected void AddAttributesProfile(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("human", usersRepo.getReferenceById(getUser().getId()));
    }

    protected void AddAttributesScore(Model model) {
        AddAttributes(model);
        model.addAttribute("human", usersRepo.getReferenceById(getUser().getId()));
    }


    protected void AddAttributesHumanEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesEmployeeEdit(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesCatalog(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("humans", usersRepo.findAllByRole(Role.STUDENT));
    }

    protected void AddAttributesEmployees(Model model) {
        AddAttributes(model);
        model.addAttribute("employees", usersRepo.findAllByRole(Role.EMPLOYEE));
    }

    protected void AddAttributesReviews(Model model) {
        AddAttributes(model);
        model.addAttribute("reviews", reviewsRepo.findAll());
    }

    protected void AddAttributesApps(Model model) {
        AddAttributes(model);
        if (getRole().equals(Role.EMPLOYEE.toString())) {
            model.addAttribute("apps", appsRepo.findAll());
        } else if (getRole().equals(Role.STUDENT.toString())) {
            model.addAttribute("apps", appsRepo.findAllByOwner(getUser()));
        }
    }

    protected void AddAttributesCatalogSearch(Model model, String group, Marital marital, Citizenship citizenship, YesNo conscripted) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("humans", usersRepo.findAllByRoleAndSecondary_JobContainingAndTertiary_MaritalAndTertiary_CitizenshipAndTertiary_Conscripted(Role.STUDENT, group, marital, citizenship, conscripted));
        model.addAttribute("marSelect", marital);
        model.addAttribute("gpSelect", group);
        model.addAttribute("citSelect", citizenship);
        model.addAttribute("conSelect", conscripted);
    }

    protected void AddAttributesStats(Model model) {
        AddAttributes(model);
        HashMap<String, Integer> maritals = new HashMap<>();
        HashMap<String, Integer> citizenships = new HashMap<>();
        HashMap<String, Integer> conscripteds = new HashMap<>();
        for (Marital i : Marital.values()) {
            maritals.put(i.getName(), usersRepo.findAllByRoleAndTertiary_Marital(Role.STUDENT, i).size());
        }
        for (Citizenship i : Citizenship.values()) {
            citizenships.put(i.getName(), usersRepo.findAllByRoleAndTertiary_Citizenship(Role.STUDENT, i).size());
        }
        for (YesNo i : YesNo.values()) {
            conscripteds.put(i.getName(), usersRepo.findAllByRoleAndTertiary_Conscripted(Role.STUDENT, i).size());
        }
        model.addAttribute("maritals", maritals);
        model.addAttribute("citizenships", citizenships);
        model.addAttribute("conscripteds", conscripteds);

        List<Score> scores = scoreRepo.findAll();

        scores.sort(Comparator.comparing(Score::getSummary));
        Collections.reverse(scores);

        String[] topQuantityName = new String[5];
        int[] topQuantityNumber = new int[5];
        for (int i = 0; i < scores.size(); i++) {
            if (i == 5) break;
            topQuantityName[i] = scores.get(i).getOwner().getPrimarys().getFIO();
            topQuantityNumber[i] = scores.get(i).getSummary();
        }
        model.addAttribute("topQuantityName", topQuantityName);
        model.addAttribute("topQuantityNumber", topQuantityNumber);


    }
}
