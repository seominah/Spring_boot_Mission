package dev.sma.basic.controller;

import dev.sma.basic.BasicApplication;
import dev.sma.basic.model.UserDto;
import dev.sma.basic.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BasicApplication.class
)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@AutoConfigureTestDatabase
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setDtos(){
        createTestUser("user01", "user01pwd", true);
        createTestUser("user02", "user02pwd", false);
    }

    private UserDto createTestUser(String username, String password, Boolean shopOwner) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setShopOwner(shopOwner);
        return userDto;
    }

    @Test
    public void givenValidId_whenLoginGet_then200() throws Exception {

    }

    @Test
    public void givenValidId_whenSignupPost_then200() {
        // given
        UserDto newUser = new UserDto();
        newUser.setUsername("user03");
        newUser.setPassword("user04pwd");
        newUser.setShopOwner(true);
        // when
        Long saveId = userService.create(newUser).getId();
        // then
        UserDto found = userService.read(saveId);
        assertThat(found.getUsername()).isEqualTo(newUser.getUsername());
        assertThat(passwordEncoder.matches("user04pwd", found.getPassword())).isTrue();
        assertThat(found.isShopOwner()).isEqualTo(true);
    }
}