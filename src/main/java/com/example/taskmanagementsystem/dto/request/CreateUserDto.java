package com.example.taskmanagementsystem.dto.request;

import com.example.taskmanagementsystem.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateUserDto {

    @NotNull
    @Length(min = 3, max = 20)
    private String name;
    @NotNull
    @Length(min = 3, max = 20)
    private String lastName;

    @Length(min = 3, max = 20)
    private String patronymic;
    @NotNull(message = "Почта не должна быть пустой пустое")
    private String email;
    @NotNull(message = "Пароль не должна быть пустой пустое")
    private String password;

    public CreateUserDto(String name, String lastName, String patronymic, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
    }

    public CreateUserDto() {
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
