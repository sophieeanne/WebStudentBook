package com.example.webstudentbook;

public class User {
    int id;
    String username;
    String password;
    String role;
    String email;

    // Constructors
    public User(){

    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, String role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    // Getters and setters
    int getId() {
        return id;
    }
    void setId(int id) {
        this.id = id;
    }
    String getUsername() {
        return username;
    }
    void setUsername(String username) {
        this.username = username;
    }
    String getPassword() {
        return password;
    }
    void setPassword(String password) {
        this.password = password;
    }
    String getRole() {
        return role;
    }
    void setRole(String role) {
        this.role = role;
    }
    String getEmail() {
        return email;
    }
    void setEmail(String email) {
        this.email = email;
    }

}
