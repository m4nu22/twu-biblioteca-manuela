package com.twu.biblioteca.interfaces;

public interface ISessionController {

    boolean login(String libraryId, String password);
    boolean logout();
    String getCurrentUserLibraryId();
    void setCurrentUserLibraryId(String libraryId);
}
