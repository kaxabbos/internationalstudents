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
@RequestMapping("/employees")
public class EmployeesCont extends Attributes {

    @GetMapping
    public String employees(Model model) {
        AddAttributesEmployees(model);
        return "employees";
    }

    @GetMapping("/edit/{id}")
    public String employeeEdit(Model model, @PathVariable Long id) {
        AddAttributesEmployeeEdit(model, id);
        return "employee_edit";
    }

    @PostMapping("/edit/{id}")
    public String employeeEdit(
            Model model, @PathVariable Long id, @RequestParam MultipartFile photo, @RequestParam String surname,
            @RequestParam String name, @RequestParam String patronymic, @RequestParam String faculty,
            @RequestParam String tel) {
        Users user = usersRepo.getReferenceById(id);
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = "employees/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + result));
                user.getEmployee().setPhoto(result);
            }
        } catch (IOException e) {
            AddAttributesEmployeeEdit(model, id);
            model.addAttribute("message", "Некорректные данные!");
            return "employee_edit";
        }

        Employees employee = user.getEmployee();
        employee.set(surname, name, patronymic, faculty, tel);

        usersRepo.save(user);

        return "redirect:/employees";
    }
}
