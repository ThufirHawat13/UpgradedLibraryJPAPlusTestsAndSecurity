package com.library.controllers;

import com.library.dao.BookDAO;
import com.library.dao.PersonDAO;
import com.library.models.Book;
import com.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {

        model.addAttribute("books", bookDAO.index());
        model.addAttribute("name", new String());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Person bookHolder = bookDAO.showHolder(id);
        if(bookHolder == null) model.addAttribute("people", personDAO.index());
        else model.addAttribute("holder", bookHolder);

        model.addAttribute("person", new Person());
        model.addAttribute("book", bookDAO.show(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {

    Book book = new Book();
    model.addAttribute("book", book);
    return "books/new";
    }

    @PostMapping()
    public String create(Model model,@Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "books/new";

        model.addAttribute("book", book);
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, Model model, @Valid Book book) {

        model.addAttribute("book", book);
        bookDAO.update(id, book);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setHolder")
    public String setHolder(@PathVariable("id") int id, Model model, Person person) {


        model.addAttribute("person", person);
        bookDAO.setHolder(person, id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/releaseHolder")
    public String releaseHolder(@PathVariable("id") int id) {

        bookDAO.releaseHolder(id);
        return "redirect:/books/" + id;
    }

    @PostMapping("/search")
    public String searchBook(@RequestParam("name") String name, Model model) {

        model.addAttribute("result" ,bookDAO.searchBook(name));
        return "books/search";
    }




}
