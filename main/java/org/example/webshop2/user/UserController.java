package org.example.webshop2.user;

import org.example.webshop2.models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<CustomUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{userID}")
    public Optional<CustomUser> getUser(@PathVariable("userID") Long userId){
        return userService.getUser(userId);
    }

    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @DeleteMapping(path = "/delete/{userID}")
    public void deleteUser(@PathVariable("userID") Long userId) {
        userService.deleteUser(userId);
    }


    @PutMapping("/update/{userID}")
    public ResponseEntity<String> updateUser(@PathVariable Long userID, @RequestBody UserDTO updatedUser) {
        userService.updateUser(userID, updatedUser);
        return ResponseEntity.ok("Updated user: " + userID);
    }
}
