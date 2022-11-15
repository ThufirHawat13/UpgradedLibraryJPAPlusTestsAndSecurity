package com.library.security;

import com.library.models.LibraryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LibraryUserDetails implements UserDetails {

    private final LibraryUser libraryUser;

    @Autowired
    public LibraryUserDetails(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.libraryUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.libraryUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
