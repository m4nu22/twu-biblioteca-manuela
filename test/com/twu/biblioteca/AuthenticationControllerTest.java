package com.twu.biblioteca;

import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AuthenticationControllerTest {

    private AuthenticationController authController;
    @Before
    public void initialize(){
        authController = new AuthenticationController();
        authController.setUsers(getDefaultUserList());
    }

    @Test
    public void login_validUser_returnsTrue() {
        //act
        boolean isLogged = authController.authenticate("123-4567","myPassword");

        //assert
        assertThat(isLogged,is(true));
    }

    @Test
    public void login_invalidLibraryNumber_returnsFalse() {
        //act
        boolean isLogged = authController.authenticate("777-9599","pass");

        //assert
        assertThat(isLogged,is(false));
    }

    @Test
    public void login_invalidPassword_returnsFalse() {
        //act
        boolean isLogged = authController.authenticate("456-7891","def");

        //assert
        assertThat(isLogged,is(false));
    }

    public List<User> getDefaultUserList(){
        User u1 = new User("123-4567","myPassword","Manu","manu@gmail.com","977383474", UserRole.customer);
        User u2 = new User("234-5678","pass","Amanda","amanda@gmail.com","9836485780", UserRole.customer);
        User u3 = new User("345-6789","123","Irene","irene@gmail.com","905836252", UserRole.librarian);
        User u4 = new User("456-7891","abc","Pedro","pedro@gmail.com","976675843", UserRole.customer);
        return Arrays.asList(u1,u2,u3,u4);
    }
}