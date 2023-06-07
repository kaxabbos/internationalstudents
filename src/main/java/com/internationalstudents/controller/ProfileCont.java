package com.internationalstudents.controller;

import com.internationalstudents.controller.main.Attributes;
import com.internationalstudents.model.*;
import com.internationalstudents.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class ProfileCont extends Attributes {

    @GetMapping
    public String Profile(Model model) {
        AddAttributesProfile(model);
        return "profile";
    }

    @GetMapping("/edit")
    public String ProfileEdit(Model model) {
        AddAttributesProfile(model);
        return "profile_edit";
    }

    @GetMapping("/score")
    public String ProfileScore(Model model) {
        AddAttributesScore(model);
        return "profile_score";
    }

    @PostMapping("/score")
    public String ProfileScore(@RequestParam int com, @RequestParam int team, @RequestParam int stress, @RequestParam int ind, @RequestParam int dis) {
        Score score = usersRepo.getReferenceById(getUser().getId()).getScore();
        score.setCom(com);
        score.setTeam(team);
        score.setStress(stress);
        score.setInd(ind);
        score.setDis(dis);
        score.setSummary(com + team + stress + ind + dis);
        scoreRepo.save(score);
        return "redirect:/profile";
    }

    @GetMapping("/vacancy")
    public String ProfileVacancy() {
        vacancyRepo.save(new Vacancy(getUser()));
        return "redirect:/profile";
    }

    @PostMapping("/edit/")
    public String ProfileEdit(
            Model model, @RequestParam MultipartFile photo, @RequestParam String surname,
            @RequestParam String name, @RequestParam String patronymic, @RequestParam String passport,
            @RequestParam String passport_number, @RequestParam String date, @RequestParam String issued,
            @RequestParam String issued_date, @RequestParam String identity, @RequestParam String address,
            @RequestParam String tel_mob, @RequestParam String tel_home, @RequestParam String email,
            @RequestParam String job, @RequestParam String post,
            @RequestParam Marital marital, @RequestParam Citizenship citizenship,
            @RequestParam YesNo conscripted) {
        Users user = usersRepo.getReferenceById(getUser().getId());
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = "humans/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + result));
                user.getPrimarys().setPhoto(result);
            }
        } catch (IOException e) {
            AddAttributesProfile(model);
            model.addAttribute("message", "Некорректные данные!");
            return "profile_edit";
        }

        Primarys primarys = user.getPrimarys();
        primarys.set(surname, name, patronymic, passport, passport_number);
        Secondary secondary = user.getSecondary();
        secondary.set(date, issued, issued_date, identity, address, tel_mob, tel_home, email, job, post);
        Tertiary tertiary = user.getTertiary();
        tertiary.set(marital, citizenship, conscripted);

        usersRepo.save(user);

        return "redirect:/profile";
    }
}
