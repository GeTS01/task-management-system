package com.example.taskmanagementsystem.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// todo написать валидации
public class CreateAuthorizeDto {
    @Email
    private String email;
    @NotNull
    @Size(min = 6, max = 20)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
