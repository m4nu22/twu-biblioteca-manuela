package com.twu.biblioteca.models;

import com.twu.biblioteca.enums.UserRole;

public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phone;
    private UserRole role;

    public User(String libraryNumber, String password, String name, String email, String phone, UserRole role) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return  "Name: " + getName() +
                " | Email: " + getEmail() +
                " | Phone='" + getPhone();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User u = (User)obj;
        return u.libraryNumber == libraryNumber;
    }
}
