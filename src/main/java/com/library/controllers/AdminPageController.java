package com.library.controllers;

import com.library.models.LibraryUser;
import com.library.services.LibraryUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminPageController {

    private final LibraryUserService libraryUserService;

    @Autowired
    public AdminPageController(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @GetMapping("/admin")
    public String getAdminPage(@ModelAttribute("users")List<LibraryUser> users) {

        users =
    }


}
