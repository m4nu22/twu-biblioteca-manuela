package com.twu.biblioteca;

import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AuthenticationControllerTest {

    private AuthenticationController authController;

    @Before
    public void initialize() {
        authController = new AuthenticationController();
        authController.setUsers(getDefaultUserList());
    }

    @Test
    public void login_emptyFiels_returnsFalse() {
        //act
        User user = authController.login("", "");

        //assert
        assertThat(user, is(nullValue()));
    }

    @Test
    public void login_nullFiels_returnsFalse() {
        //act
        User user = authController.login(null, null);

        //assert
        assertThat(user, is(nullValue()));
    }

    @Test
    public void login_validUser_returnsTrue() {
        //act
        User user = authController.login("123-4567", "myPassword");

        //assert
        assertThat(user, is(notNullValue()));
    }

    @Test
    public void login_invalidLibraryNumber_returnsFalse() {
        //act
        User user = authController.login("777-9599", "pass");

        //assert
        assertThat(user, is(nullValue()));
    }

    @Test
    public void login_invalidPassword_returnsFalse() {
        //act
        User user = authController.login("456-7891", "def");

        //assert
        assertThat(user, is(nullValue()));
    }


    public List<User> getDefaultUserList() {
        User u1 = new User("123-4567", "myPassword", "Manu", "manu@gmail.com", "977383474", UserRole.CUSTOMER);
        User u2 = new User("234-5678", "pass", "Amanda", "amanda@gmail.com", "9836485780", UserRole.CUSTOMER);
        User u3 = new User("345-6789", "123", "Irene", "irene@gmail.com", "905836252", UserRole.LIBRARIAN);
        User u4 = new User("456-7891", "abc", "Pedro", "pedro@gmail.com", "976675843", UserRole.CUSTOMER);
        return Arrays.asList(u1, u2, u3, u4);
    }
}
