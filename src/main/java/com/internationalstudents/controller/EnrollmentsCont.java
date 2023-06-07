package com.internationalstudents.controller;

import com.internationalstudents.controller.main.Attributes;
import com.internationalstudents.model.Users;
import com.internationalstudents.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/enrollments")
public class EnrollmentsCont extends Attributes {

    @GetMapping
    public String enrollments(Model model) {
        AddAttributesEnrollments(model);
        return "enrollments";
    }

    @GetMapping("/enrollment/{id}")
    public String enrollments(@PathVariable Long id) {
        Users user = usersRepo.getReferenceById(id);
        user.setRole(Role.STUDENT);
        usersRepo.save(user);
        return "redirect:/enrollments";
    }
}
