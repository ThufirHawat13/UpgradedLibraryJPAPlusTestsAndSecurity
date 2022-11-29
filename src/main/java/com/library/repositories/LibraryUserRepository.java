package com.library.repositories;

import com.library.models.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {

  Optional<LibraryUser> findByUsername(String username);
}
