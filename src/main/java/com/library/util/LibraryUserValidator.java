package com.library.util;

import com.library.models.LibraryUser;
import com.library.services.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LibraryUserValidator implements Validator {

    private final LibraryUserService libraryUserService;

    @Autowired
    public LibraryUserValidator(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LibraryUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LibraryUser libraryUser = (LibraryUser) target;

        if(libraryUserService.findByUsername(libraryUser.getUsername()) !=null) {
            errors.rejectValue("username", "",
                    "This username is already exists in database!");
        }
    }
}
