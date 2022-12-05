package com.library.services;

import com.library.models.LibraryUser;
import com.library.repositories.LibraryUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RegistrationServiceTest {

  private final RegistrationService registrationService;

  @Autowired
  RegistrationServiceTest(RegistrationService registrationService) {
    this.registrationService = registrationService;
  }

  @MockBean
  private LibraryUserRepository libraryUserRepository;

  @MockBean
  private PasswordEncoder passwordEncoder;

  @Mock
  private LibraryUser libraryUser;

  @Test
  void register() {
    registrationService.register(libraryUser);

    Mockito.verify(libraryUserRepository, Mockito.times(1))
        .save(libraryUser);
  }
}