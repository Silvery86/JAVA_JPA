package com.t2208e.T2208E_Java_JPA.controller;

import com.t2208e.T2208E_Java_JPA.dto.PageDto;
import com.t2208e.T2208E_Java_JPA.dto.UserDto;
import com.t2208e.T2208E_Java_JPA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        userDto.setId(id);  // Ensure the ID in the DTO matches the path variable
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/search")
    public ResponseEntity<PageDto> searchUsers(@RequestBody UserDto userDto) {
        PageDto pageDto = userService.search(userDto);
        return ResponseEntity.ok(pageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userService.getUserByIdWithDetails(id);
    }
}
