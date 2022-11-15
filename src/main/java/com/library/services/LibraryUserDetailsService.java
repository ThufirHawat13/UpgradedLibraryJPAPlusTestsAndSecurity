package com.library.services;

import com.library.models.LibraryUser;
import com.library.repositories.LibraryUserRepository;
import com.library.security.LibraryUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

    private final LibraryUserRepository libraryUserRepository;

    @Autowired
    public LibraryUserDetailsService(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(libraryUserRepository.findAll());
        LibraryUser libraryUser = libraryUserRepository.findByUsername(username).orElse(null);

        System.out.println(libraryUser);
        if(libraryUser == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new LibraryUserDetails(libraryUser);
    }
}
