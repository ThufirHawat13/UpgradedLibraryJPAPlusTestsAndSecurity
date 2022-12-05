package com.library.controllers;

import com.library.services.LibraryUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdminPageControllerTest {

  private final AdminPageController adminPageController;

  @Autowired
  AdminPageControllerTest(AdminPageController adminPageController) {
    this.adminPageController = adminPageController;
  }

  @MockBean
  private LibraryUserService libraryUserService;

  @Mock
  private Model model;


  @Test
  void getAdminPage() {
    adminPageController.getAdminPage(model);

    Mockito.verify(libraryUserService, Mockito.times(1))
        .showAll();
  }

  @Test
  void deleteUser() {
    int id = 0;
    adminPageController.deleteUser(id);

    Mockito.verify(libraryUserService, Mockito.times(1))
        .delete(id);
  }
}