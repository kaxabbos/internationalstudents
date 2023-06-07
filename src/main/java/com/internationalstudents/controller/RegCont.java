package com.internationalstudents.controller;

import com.internationalstudents.controller.main.Attributes;
import com.internationalstudents.model.Primarys;
import com.internationalstudents.model.Users;
import com.internationalstudents.model.enums.Citizenship;
import com.internationalstudents.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Attributes {

    @GetMapping
    public String reg(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String surname, @RequestParam String name, @RequestParam String patronymic, @RequestParam String passport_number, @RequestParam Citizenship citizenship) {
        if (usersRepo.findAll().isEmpty() || usersRepo.findAll().size() == 0) {
            usersRepo.save(new Users(username, password, Role.ADMIN));
            return "redirect:/login";
        }

        if (usersRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            AddAttributesEnums(model);
            AddAttributes(model);
            return "reg";
        }

        Users  user = new Users(username, password, Role.USER);

        Primarys primary = user.getPrimarys();
        primary.setSurname(surname);
        primary.setName(name);
        primary.setPatronymic(patronymic);
        primary.setPassport_number(passport_number);
        user.getTertiary().setCitizenship(citizenship);

        usersRepo.save(user);

        return "redirect:/login";
    }
}
