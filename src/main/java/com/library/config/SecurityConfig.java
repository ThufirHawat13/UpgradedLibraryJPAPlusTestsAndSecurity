package com.library.config;

import com.library.services.LibraryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final LibraryUserDetailsService libraryUserDetailsService;

  @Autowired
  public SecurityConfig(LibraryUserDetailsService libraryUserDetailsService) {
    this.libraryUserDetailsService = libraryUserDetailsService;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/admin").hasAuthority("ROLE_ADMIN")
        .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
        .anyRequest().hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
        .and()
        .formLogin().loginPage("/auth/login")
        .loginProcessingUrl("/process_login")
        .defaultSuccessUrl("/", true)
        .failureUrl("/auth/login")
        .and()
        .logout().logoutUrl("/auth/logout")
        .logoutSuccessUrl("/auth/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(libraryUserDetailsService)
        .passwordEncoder(getPasswordEncoder());
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
