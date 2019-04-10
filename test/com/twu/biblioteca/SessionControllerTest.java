package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IAuthenticationController;
import com.twu.biblioteca.interfaces.ISessionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

public class SessionControllerTest {

    IAuthenticationController authControllerMock;
    ISessionController sessionController;

    @Before
    public void initialize(){
        authControllerMock = Mockito.mock(IAuthenticationController.class);
        sessionController = new SessionController(authControllerMock);
    }

    @Test
    public void login_valid_updatesCurrentUserLibraryId(){
        //arrange
        Mockito.when(authControllerMock.authenticate(anyString(),anyString())).thenReturn(true);

        //act
        sessionController.login("123-4556","mypass");

        //assert
        assertThat(sessionController.getCurrentUserLibraryId(),is("123-4556"));
    }

    @Test
    public void login_invalid_doesntUpdateCurrentUserLibraryId(){
        //arrange
        Mockito.when(authControllerMock.authenticate(anyString(),anyString())).thenReturn(false);

        //act
        sessionController.login("723-4155","pass1");

        //assert
        assertThat(sessionController.getCurrentUserLibraryId(),is(""));
    }

    @Test
    public void logout_loggedUser_updatesCurrentUserLibraryId(){
        //arrange
        sessionController.setCurrentUserLibraryId("123-4567");

        //act
        sessionController.logout();

        //assert
        assertThat(sessionController.getCurrentUserLibraryId(),is(""));
    }

    @Test
    public void logout_notLoggedUser_doesntUpdateCurrentUserLibraryId(){

        //act
        sessionController.logout();

        //assert
        assertThat(sessionController.getCurrentUserLibraryId(),is(""));
    }
}
