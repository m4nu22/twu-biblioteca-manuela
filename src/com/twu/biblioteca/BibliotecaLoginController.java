package com.twu.biblioteca;

import com.twu.biblioteca.interfaces.IConsole;
import com.twu.biblioteca.interfaces.IPrinter;
import com.twu.biblioteca.models.User;

public class BibliotecaLoginController {

    private IPrinter printer;
    private IConsole console;
    private AuthenticationController authController;


    public BibliotecaLoginController(IPrinter printer, IConsole console) {
        this.printer = printer;
        this.console = console;
        this.authController = new AuthenticationController();
    }

    public User login() {
        printer.printLn("Please type your library ID number");
        String libraryId = console.readString();

        printer.printLn("Please type your password");
        String password = console.readString();

        User user = authController.login(libraryId, password);

        if (user == null)
            printer.printLn("Please verify your credentials.\n");

        return user;

    }
}
