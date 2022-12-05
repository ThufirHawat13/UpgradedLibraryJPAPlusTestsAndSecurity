package com.library.services;

import com.library.models.LibraryUser;
import com.library.repositories.LibraryUserRepository;
import com.library.security.LibraryUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibraryUserDetailsServiceTest {

  private final LibraryUserDetailsService libraryUserDetailsService;

  @Autowired
  LibraryUserDetailsServiceTest(LibraryUserDetailsService libraryUserDetailsService) {
    this.libraryUserDetailsService = libraryUserDetailsService;
  }

  @MockBean
  private LibraryUserRepository libraryUserRepository;

  @Mock
  private LibraryUser libraryUser;

  @Mock
  private UserDetails userDetails;

  @Test
  void loadUserByUsername() {
    String username = "n";
    Mockito.when(libraryUserRepository.findByUsername(username))
        .thenReturn(Optional.of(libraryUser));
    userDetails = libraryUserDetailsService.loadUserByUsername(username);

    Mockito.verify(libraryUserRepository, Mockito.times(1))
        .findByUsername(username);
  }
}