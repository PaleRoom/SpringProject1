package ru.nicolas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nicolas.dao.PersonDAO;
import ru.nicolas.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDao;

    @Autowired
    public AdminController(PersonDAO personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String adminPage (Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDao.index());
        return  "people/adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }
}
