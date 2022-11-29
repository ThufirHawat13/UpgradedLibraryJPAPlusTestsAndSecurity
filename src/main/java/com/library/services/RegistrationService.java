package com.library.services;


import com.library.models.LibraryUser;
import com.library.repositories.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

  private final LibraryUserRepository libraryUserRepository;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public RegistrationService(LibraryUserRepository libraryUserRepository,
      PasswordEncoder passwordEncoder) {
    this.libraryUserRepository = libraryUserRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional(readOnly = false)
  public void register(LibraryUser libraryUser) {
    libraryUser.setPassword(passwordEncoder.encode(libraryUser.getPassword()));
    libraryUser.setRole("ROLE_USER");
    libraryUserRepository.save(libraryUser);
  }

}
