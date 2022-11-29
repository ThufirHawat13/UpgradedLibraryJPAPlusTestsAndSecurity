package com.library.controllers;

import com.library.models.LibraryUser;
import com.library.services.RegistrationService;
import com.library.util.LibraryUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final LibraryUserValidator libraryUserValidator;

  private final RegistrationService registrationService;


  @Autowired
  public AuthController(LibraryUserValidator libraryUserValidator,
      RegistrationService registrationService) {
    this.libraryUserValidator = libraryUserValidator;
    this.registrationService = registrationService;
  }

  @GetMapping("/login")
  public String login() {

    return "auth/login";
  }

  @GetMapping("/registration")
  public String registrationPage(@ModelAttribute("libraryUser") LibraryUser libraryUser) {

    return "auth/registration";
  }

  @PostMapping("/registration")
  public String registerNewUser(@ModelAttribute("libraryUser") @Valid LibraryUser libraryUser,
      BindingResult bindingResult) {
    libraryUserValidator.validate(libraryUser, bindingResult);
    if (bindingResult.hasErrors()) {
      return "auth/registration";
    }

    registrationService.register(libraryUser);

    return "redirect:/auth/login";
  }


}
