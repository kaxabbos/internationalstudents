package com.internationalstudents.controller;

import com.internationalstudents.controller.main.Attributes;
import com.internationalstudents.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1() {
        return "redirect:/about";
    }

    @GetMapping("/index")
    public String index2() {
        return "redirect:/about";
    }

    @GetMapping("/catalog")
    public String index2(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam String group,  @RequestParam Marital marital, @RequestParam Citizenship citizenship, @RequestParam YesNo conscripted) {
        AddAttributesCatalogSearch(model,group, marital, citizenship, conscripted);
        return "catalog";
    }
}
