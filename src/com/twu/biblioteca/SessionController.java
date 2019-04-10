package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IAuthenticationController;
import com.twu.biblioteca.interfaces.ISessionController;

public class SessionController implements ISessionController {

    private String currentUserLibraryId = "";
    private IAuthenticationController authController;

    public SessionController(IAuthenticationController authController){
        this.authController = authController;
    }

    public boolean login(String libraryId, String password) {
        boolean isAuth = authController.authenticate(libraryId,password);
        if(isAuth)
            setCurrentUserLibraryId(libraryId);
        return isAuth;
    }

    public boolean logout() {
        boolean isLoggedOut = false;

        if(!currentUserLibraryId.equals("")) {
            setCurrentUserLibraryId("");
            isLoggedOut = true;
        }
        return isLoggedOut;
    }

    public String getCurrentUserLibraryId(){
        return this.currentUserLibraryId;
    }

    public void setCurrentUserLibraryId(String libraryId){
        this.currentUserLibraryId = libraryId;
    }
}
