package org.launchcode.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {
    @NotNull @Size(min = 3, max = 15)
    private String username;

    @Email
    private String email;

    @NotNull @Size(min = 6)
    private String password;

    @NotNull(message = "Passwords do not match.")
    private String verifyPassword;

    private Date date;

    private int userId;
    private static int nextId = 1;

    public User () {
        this.date = new Date();
        this.userId = nextId++;
    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private void checkPassword() {
        if (this.password != this.verifyPassword && this.password != null && this.verifyPassword != null) {
            this.verifyPassword = null;
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        checkPassword();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    public Date getDate() {
        return date;
    }
}
