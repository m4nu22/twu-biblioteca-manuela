package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IAuthenticationController;
import com.twu.biblioteca.models.User;

import java.util.List;

public class AuthenticationController implements IAuthenticationController {

    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean authenticate(String libraryNumber, String password) {
        return users.stream().anyMatch(u -> libraryNumber.equals(u.getLibraryNumber()) && password.equals(u.getPassword()));
    }
}
