package com.library.models;

import javax.persistence.*;

@Entity
@Table(name = "library_user")
public class LibraryUser {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "username")
  private String username;

  @Column(name = "user_password")
  private String password;

  @Column(name = "role")
  private String role;

  public LibraryUser(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public LibraryUser() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

}
