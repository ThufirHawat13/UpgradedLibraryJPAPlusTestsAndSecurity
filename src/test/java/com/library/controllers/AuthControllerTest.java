package com.library.controllers;

import com.library.models.LibraryUser;
import com.library.services.RegistrationService;
import com.library.util.LibraryUserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthControllerTest {

  private final AuthController authController;

  @Autowired
  AuthControllerTest(AuthController authController) {
    this.authController = authController;
  }

  @MockBean
  private LibraryUserValidator libraryUserValidator;

  @MockBean
  private RegistrationService registrationService;

  @Mock
  private LibraryUser libraryUser;

  @Mock
  private BindingResult bindingResult;

  @Test
  void registerNewUser() {
    authController.registerNewUser(libraryUser, bindingResult);

    Mockito.verify(libraryUserValidator, Mockito.times(1))
        .validate(libraryUser, bindingResult);

    Mockito.verify(registrationService, Mockito.times(1))
        .register(libraryUser);
  }
}