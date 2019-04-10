package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.models.User;

import java.util.List;

public interface IAuthenticationController {

    void setUsers(List<User> users);

    User login(String libraryId, String password);

    boolean logout();
}
