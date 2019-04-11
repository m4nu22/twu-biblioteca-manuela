package com.twu.biblioteca;

import com.twu.biblioteca.enums.UserRole;
import com.twu.biblioteca.interfaces.IMenuOptionHandler;
import com.twu.biblioteca.models.Book;
import com.twu.biblioteca.models.LibraryItem;
import com.twu.biblioteca.models.Movie;
import com.twu.biblioteca.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Console console = new Console();

        initializeLibrary();

        while (true) {
            User user = libraryLogin(printer, console);

            ShowLibraryWelcomeMsgAndMenu(printer, console, user);
        }
    }

    private static void initializeLibrary() {
        Library library = Library.getInstance();
        List<LibraryItem> items = addItemsToLibrary();
        library.setItems(items);
    }

    private static ArrayList<LibraryItem> addItemsToLibrary() {
        Book hp = new Book(1, "Harry Potter", "J.K. Rolling", "2000");
        Book narnia = new Book(2, "Narnia", "C. S. Lewis", "2003");
        Movie memento = new Movie(1, "Memento", "2000", "Christopher Nolan", 10);
        Movie contratiempo = new Movie(2, "Contratiempo", "2017", "Oriol Paulo");
        Movie aStarIsBorn = new Movie(2, "A star is born", "2018", "Bradley Cooper", 8);
        return new ArrayList<>(Arrays.asList(hp, narnia, memento, contratiempo, aStarIsBorn));
    }

    private static User libraryLogin(Printer printer, Console console) {
        BibliotecaLoginController loginController = new BibliotecaLoginController(printer, console);

        User user;
        while ((user = loginController.login()) == null) ;

        return user;
    }

    private static void ShowLibraryWelcomeMsgAndMenu(Printer printer, Console console, User user) {
        IMenuOptionHandler handler = getMenuOptionHandler(printer, console, user);

        LibraryMenu libraryMenu = new LibraryMenu(printer, console, handler);
        libraryMenu.printWelcomeMessage();

        libraryMenu.showMenuAndHandleOptionSelection(user.getRole());
    }

    private static IMenuOptionHandler getMenuOptionHandler(Printer printer, Console console, User user) {
        IMenuOptionHandler handler;
        if (UserRole.CUSTOMER.equals(user.getRole()))
            handler = new CustomerMenuOptionHandler(printer, console, Library.getInstance(), user);
        else
            handler = new LibrarianMenuOptionHandler(printer, Library.getInstance());
        return handler;
    }
}
