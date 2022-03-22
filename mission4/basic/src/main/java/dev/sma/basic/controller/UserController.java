package dev.sma.basic.controller;

import dev.sma.basic.jpa.entity.UserEntity;
import dev.sma.basic.model.UserDto;
import dev.sma.basic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login-form";
    }

    @GetMapping("signup")
    public String signup() {
        return "signup-form";
    }

    @PostMapping("signup")
    public String signupPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password_check") String passwordCheck,
            @RequestParam(name = "shopOwner", required = false) String shopOwner) {
        if (!password.equals(passwordCheck)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        if (shopOwner != null) {
            userDto.setShopOwner(true);
        }
        this.userService.create(userDto);
        return "redirect:/user/login";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        UserDto result = this.userService.create(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> readUser(@PathVariable("id") Long id) {
        UserDto dto = this.userService.read(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<UserDto>> readAllUser() {
        Collection<UserDto> userList = this.userService.readAll();
        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto dto) {
        if (!this.userService.update(id, dto)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        if (!this.userService.delete(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
