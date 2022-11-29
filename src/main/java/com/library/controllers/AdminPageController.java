package com.library.controllers;

import com.library.models.LibraryUser;
import com.library.services.LibraryUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminPageController {

  private final LibraryUserService libraryUserService;

  @Autowired
  public AdminPageController(LibraryUserService libraryUserService) {
    this.libraryUserService = libraryUserService;
  }

  @GetMapping("/admin")
  public String getAdminPage(Model model) {
    model.addAttribute("users", libraryUserService.showAll());
    return "/admin/adminPage";
  }

  @DeleteMapping("/admin/{id}")
  public String deleteUser(@PathVariable("id") int id) {
    libraryUserService.delete(id);
    return "redirect:/admin";
  }


}
