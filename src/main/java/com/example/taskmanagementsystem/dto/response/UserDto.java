package com.example.taskmanagementsystem.dto.response;

import com.example.taskmanagementsystem.domain.User;

public class UserDto {

    private long id;
    private String name;
    private String lastName;
    private String patronymic;
    private String email;
    private String password;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.patronymic = user.getPatronymic();
        this.email = user.getEmail();
        this.password = user.getEmail();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
