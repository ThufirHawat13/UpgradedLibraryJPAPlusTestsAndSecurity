package com.library.services;

import com.library.models.LibraryUser;
import com.library.repositories.LibraryUserRepository;
import com.library.security.LibraryUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

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
    private LibraryUserDetails libraryUserDetails;

    @Test
    void loadUserByUsername() {
        String username = "username";
        Mockito.when(libraryUserRepository.findByUsername(username)).thenReturn(Optional.of(libraryUser));
        libraryUserDetailsService.loadUserByUsername(username);

        Mockito.verify(libraryUserRepository, Mockito.times(1)).findByUsername(username);
    }
}