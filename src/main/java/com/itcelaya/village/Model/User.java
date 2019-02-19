package com.itcelaya.village.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Size(min = 3, max = 255, message = "Minimum username length: 3 characters")
    public String firstname;

    @Size(min = 3, max = 255, message = "Minimum username length: 3 characters")
    public String surname;

    @Size(min = 3, max = 15, message = "Minimum username length: 3 characters")
    public String username;

    @Size(min = 8, max = 150, message = "Minimum password length: 8 characters")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
