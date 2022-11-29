package com.library.controllers;

import com.library.models.Book;
import com.library.models.Person;
import com.library.services.BookService;
import com.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {


  private final BookService bookService;

  private final PeopleService peopleService;

  @Autowired
  public BookController(BookService bookService, PeopleService peopleService) {
    this.bookService = bookService;
    this.peopleService = peopleService;
  }


  @GetMapping()
  public String index(Model model) {

    model.addAttribute("books", bookService.findAll());
    model.addAttribute("name", new String());
    return "books/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable("id") int id, Model model) {

    Book book = bookService.findById(id);
    Person bookHolder = book.getPerson();
    if (bookHolder == null) {
      model.addAttribute("people", peopleService.findAll());
    } else {
      model.addAttribute("holder", bookHolder);
    }

    model.addAttribute("person", new Person());
    model.addAttribute("book", book);
    return "books/show";
  }

  @GetMapping("/new")
  public String newBook(Model model) {

    Book book = new Book();
    model.addAttribute("book", book);
    return "books/new";
  }

  @PostMapping()
  public String create(Model model, @Valid Book book, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "books/new";
    }

    model.addAttribute("book", book);
    bookService.save(book);
    return "redirect:/books";
  }

  @GetMapping("/{id}/edit")
  public String edit(@PathVariable("id") int id, Model model) {

    model.addAttribute("book", bookService.findById(id));
    return "books/edit";
  }

  @PatchMapping("/{id}")
  public String update(@PathVariable("id") int id, Model model, @Valid Book book) {

    model.addAttribute("book", book);
    bookService.update(id, book);
    return "redirect:/books";
  }


  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id) {

    bookService.delete(id);
    return "redirect:/books";
  }

  @PatchMapping("/{id}/setHolder")
  public String setHolder(@PathVariable("id") int id, Model model, Person person) {

    model.addAttribute("person", person);
    bookService.setHolder(id, person);
    return "redirect:/books/" + id;
  }

  @PatchMapping("/{id}/releaseHolder")
  public String releaseHolder(@PathVariable("id") int id) {

    bookService.releaseHolder(id);
    return "redirect:/books/" + id;
  }

  @PostMapping("/search")
  public String searchBook(@RequestParam("name") String name, Model model) {

    model.addAttribute("result", bookService.findByName(name));
    return "books/search";
  }


}
