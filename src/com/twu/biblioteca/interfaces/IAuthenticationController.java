package com.twu.biblioteca.interfaces;

import com.twu.biblioteca.models.User;

import java.util.List;

public interface IAuthenticationController {

    void setUsers(List<User> users);

    boolean authenticate(String libraryNumber, String password);
}
