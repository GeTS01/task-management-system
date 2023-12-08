package com.example.taskmanagementsystem.dto.request;

import com.example.taskmanagementsystem.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateUserDto {

    @NotNull
    @Length(min = 3, max = 10)
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String patronymic;
    @NotNull
    private String email;
    @NotNull
    private String password;

    public CreateUserDto(String name, String lastName, String patronymic, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
    }

    public User CreateUserDto(CreateUserDto create) {
        User user = new User();
        user.setName(create.getName());
        user.setLastName(create.getLastName());
        user.setPatronymic(create.getPatronymic());
        user.setEmail(create.getEmail());
        user.setPassword(create.getPassword());
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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
