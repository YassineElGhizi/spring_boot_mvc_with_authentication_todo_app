package com.advanced_to_list.advanced_to_list.FormValidation;

public class Login {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Login{" + "email='" + email + '\'' + ", password='" + password + '\'' + '}';
    }


}

