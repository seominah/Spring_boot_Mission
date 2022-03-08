package dev.sma.basic.controller;

import dev.sma.basic.dto.UserDto;
import dev.sma.basic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public void joinUser(@RequestBody UserDto userDto) {
        this.userService.joinUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto readUser(@PathVariable("id") int id) {
        return this.userService.readUser(id);
    }

    @GetMapping()
    public List<UserDto> readAllUser() {
        return this.userService.readAllUser();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@PathVariable("id") int id, @RequestBody UserDto userDto) {
        this.userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser(@PathVariable("id") int id) {
        this.userService.deleteUser(id);
    }
}
