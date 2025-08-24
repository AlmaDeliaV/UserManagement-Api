package com.ar.usermanagementapi.api.models;

public class UpdateRequestUEP {
    private String name;
    private String email;
    private String password;

    public UpdateRequestUEP(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return name;
    }
    public void setUsername(String username) {
        this.name = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
