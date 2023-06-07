package com.internationalstudents.controller;

import com.internationalstudents.controller.main.Attributes;
import com.internationalstudents.model.Apps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/apps")
public class AppsCont extends Attributes {

    @GetMapping
    public String Apps(Model model) {
        AddAttributesApps(model);
        return "apps";
    }


    @PostMapping("/add")
    public String AppsAdd(@RequestParam String text) {
        appsRepo.save(new Apps(text, DateNow(), getUser()));
        return "redirect:/apps";
    }
}
