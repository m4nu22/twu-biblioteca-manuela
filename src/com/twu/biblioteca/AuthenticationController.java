package com.twu.biblioteca;

import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.models.User;

import java.util.Arrays;
import java.util.List;

public class AuthenticationController {

    private List<User> users;

    public AuthenticationController() {
        User u1 = new User("123-4567", "myPassword", "Manu", "manu@gmail.com", "977383474", UserRole.CUSTOMER);
        User u2 = new User("234-5678", "pass", "Amanda", "amanda@gmail.com", "9836485780", UserRole.CUSTOMER);
        User u3 = new User("345-6789", "123", "Irene", "irene@gmail.com", "905836252", UserRole.LIBRARIAN);
        User u4 = new User("456-7891", "abc", "Pedro", "pedro@gmail.com", "976675843", UserRole.CUSTOMER);
        setUsers(Arrays.asList(u1, u2, u3, u4));
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User login(String libraryId, String password) {
        return users.stream().filter(u -> (u.getLibraryNumber()).equals(libraryId) && (u.getPassword()).equals(password)).findFirst().orElse(null);
    }
}
