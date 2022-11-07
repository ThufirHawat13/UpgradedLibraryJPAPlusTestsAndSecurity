package com.library.controllers;

import com.library.dao.PersonDAO;
import com.library.models.Person;
import com.library.services.BookService;
import com.library.services.PeopleService;
import com.library.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final BookService bookService;
    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(BookService bookService, PeopleService peopleService, PersonValidator personValidator) {
        this.bookService = bookService;
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Person person = peopleService.findById(id);
        model.addAttribute("person", person);
        model.addAttribute("books", bookService.findByOwner(person));
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
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model, @Valid Person person, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) return "people/edit";
    try{
        model.addAttribute("person", person);
        peopleService.update(id, person);
        return "redirect:/people";
    }catch(Exception e) {
        personValidator.validate(person, bindingResult);
        return "people/edit";
    }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        peopleService.delete(id);
        return "redirect:/people";
    }

    @PostMapping("/search")
    public String searchPersonFromName(@RequestParam("name") String name, Model model) {

        model.addAttribute("result", peopleService.findByName(name));
        return "/people/search";
    }


}
