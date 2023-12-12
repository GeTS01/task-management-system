package com.example.taskmanagementsystem.dto;

import com.example.taskmanagementsystem.domain.User;
import com.example.taskmanagementsystem.domain.enums.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class UserRegistrationDto {
    @NotNull
    @Size(min = 3, max = 10)
    private String name;
    @NotNull
    @Size(min = 3, max = 10)
    private String lastName;
    private String patronymic;
    @Email
    private String email;
    @Size(min=6, max=20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^\\w\\s]).{8,}",
            message = """
                    Имеет длину не менее 8 символов.
                    Хотя бы одна прописная английская буква.
                    Хотя бы одна строчная буква английского языка.
                    Хотя бы одна цифра.
                    Хотя бы один специальный символ ?=.*?[ #?! @$%^&*-]
                    """
    )
    private String password;
    @NotNull
    private Role role;

    public User createRegistrationUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setName(userRegistrationDto.getLastName());
        user.setPatronymic(userRegistrationDto.getPatronymic());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        user.setRole(userRegistrationDto.getRole());
        user.setCreateAt(ZonedDateTime.now());
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
