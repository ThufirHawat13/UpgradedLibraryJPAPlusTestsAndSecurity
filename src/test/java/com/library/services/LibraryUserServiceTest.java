package com.library.services;

import com.library.repositories.LibraryUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibraryUserServiceTest {

  private final LibraryUserService libraryUserService;

  @Autowired
  LibraryUserServiceTest(LibraryUserService libraryUserService) {
    this.libraryUserService = libraryUserService;
  }

  @MockBean
  private LibraryUserRepository libraryUserRepository;


  @Test
  void findByUsername() {
    String username = "n";
    libraryUserService.findByUsername(username);

    Mockito.verify(libraryUserRepository, Mockito.times(1))
        .findByUsername(username);
  }

  @Test
  void showAll() {
    libraryUserService.showAll();

    Mockito.verify(libraryUserRepository, Mockito.times(1))
        .findAll();
  }

  @Test
  void delete() {
    int id = 0;
    libraryUserService.delete(id);

    Mockito.verify(libraryUserRepository, Mockito.times(1))
        .deleteById(id);
  }
}