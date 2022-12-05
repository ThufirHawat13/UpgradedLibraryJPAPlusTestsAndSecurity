package com.library.util;

import com.library.models.LibraryUser;
import com.library.services.LibraryUserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.Errors;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibraryUserValidatorTest {

  private final LibraryUserValidator libraryUserValidator;

  @Autowired
  LibraryUserValidatorTest(LibraryUserValidator libraryUserValidator) {
    this.libraryUserValidator = libraryUserValidator;
  }

  @MockBean
  private LibraryUserService libraryUserService;
  @Mock
  private Errors errors;
  private static final LibraryUser libraryUser = Mockito.mock(LibraryUser.class);
  private static final String username = "username";

  @BeforeAll
  public static void setup() {
    Mockito.when(libraryUser.getUsername())
        .thenReturn(username);
  }

  @Test
  void validateShouldAcceptUserWithNewNameAndSurname() {
    Mockito.when(libraryUserService.findByUsername(libraryUser.getUsername()))
        .thenReturn(null);
    libraryUserValidator.validate(libraryUser, errors);

    Mockito.verify(errors, Mockito.times(0))
        .rejectValue(ArgumentMatchers.any(),
            ArgumentMatchers.any(),
            ArgumentMatchers.any());
  }

  @Test
  void validateShouldNotAcceptUserWithNewNameAndSurname() {
    Mockito.when(libraryUserService.findByUsername(libraryUser.getUsername()))
        .thenReturn(libraryUser);
    libraryUserValidator.validate(libraryUser, errors);

    Mockito.verify(errors, Mockito.times(1))
        .rejectValue(ArgumentMatchers.any(),
            ArgumentMatchers.any(),
            ArgumentMatchers.any());
  }
}