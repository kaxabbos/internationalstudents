package com.internationalstudents.controller;

import com.internationalstudents.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatsCont extends Attributes {
    @GetMapping
    public String Stats(Model model) {
        AddAttributesStats(model);
        return "stats";
    }
}
