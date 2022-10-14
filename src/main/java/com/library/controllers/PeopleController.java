package com.library.controllers;

import com.library.dao.PersonDAO;
import com.library.models.Person;
import com.library.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.showBooks(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {

        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(Model model, @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors())  return "people/new";

        model.addAttribute("person", person);
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model, @Valid Person person, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) return "people/edit";
    try{
        model.addAttribute("person", person);
        personDAO.update(person, id);
        return "redirect:/people";
    }catch(Exception e) {
        personValidator.validate(person, bindingResult);
        return "people/edit";
    }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        personDAO.delete(id);
        return "redirect:/people";
    }

    @PostMapping("/search")
    public String searchPersonFromName(@RequestParam("name") String name, Model model) {

        model.addAttribute("result", personDAO.personSearch(name));
        return "/people/search";
    }


}
