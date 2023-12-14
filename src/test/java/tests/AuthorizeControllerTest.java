package tests;

import com.example.taskmanagementsystem.TaskManagementSystemApplication;
import com.example.taskmanagementsystem.domain.User;
import com.example.taskmanagementsystem.domain.enums.Role;
import com.example.taskmanagementsystem.dto.request.CreateAuthorizeDto;
import com.example.taskmanagementsystem.dto.request.CreateUserRegistrationDto;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.security.Token;
import com.example.taskmanagementsystem.security.enums.TokenType;
import com.example.taskmanagementsystem.security.sevice.JwtService;
import com.example.taskmanagementsystem.service.AuthorizeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TaskManagementSystemApplication.class)
@AutoConfigureMockMvc
public class AuthorizeControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private AuthorizeService authorizeService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldResponseStatusBadRequest() throws Exception {
        userRepository.deleteAll();
        CreateUserRegistrationDto dto = new CreateUserRegistrationDto(
                "test", "test", "test", "test@test.test", "Test3456!.", Role.CLIENT);
        var pas = passwordEncoder.encode(dto.getPassword());
        User user = new User(
                dto.getName(),
                dto.getLastName(),
                dto.getPatronymic(),
                dto.getEmail(),
                pas,
                dto.getRole()
        );
        userRepository.save(user);
        mvc.perform(post("/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldRegistration() throws Exception {
        userRepository.deleteAll();
        CreateUserRegistrationDto dto = new CreateUserRegistrationDto(
                "test", "test", "test", "test@test.test", "Test3456!.", Role.CLIENT);
        var pas = passwordEncoder.encode(dto.getPassword());
        User expected = new User(
                dto.getName(),
                dto.getLastName(),
                dto.getPatronymic(),
                dto.getEmail(),
                pas,
                dto.getRole()
        );
        mvc.perform(post("/user/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();
        var actual = userRepository.findByEmail("test@test.test");
        if (actual.isEmpty()) {
            throw new AssertionError();
        }
        assertEquals(expected, actual.get());
    }

    @Test
    public void shouldAuthorize() throws Exception {
        userRepository.deleteAll();
        CreateAuthorizeDto createAuthorizeDto = new CreateAuthorizeDto("test@test.com", "Test3456!.");
        var pas = passwordEncoder.encode(createAuthorizeDto.getPassword());
        User user = new User(
                "test",
                "test",
                "test",
                createAuthorizeDto.getEmail(),
                pas,
                Role.CLIENT
        );
        user.setId(5);
        user.setCreateAt(ZonedDateTime.now());
        userRepository.save(user);
        Token token = jwtService.build(clams(user), TokenType.ACCESS);


        mvc.perform(post("/user/authorize")
                        .header("Authorization", "Bearer " + token.token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createAuthorizeDto)))
                .andExpect(status().isOk());
    }

    private Map<String, Object> clams(User user) {
        return Map.of(
                "id", user.getId(),
                "email", user.getEmail(),
                "role", user.getRole().name()
        );
    }
}
