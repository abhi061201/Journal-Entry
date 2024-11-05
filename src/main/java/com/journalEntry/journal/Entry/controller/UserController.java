package com.journalEntry.journal.Entry.controller;

import com.journalEntry.journal.Entry.Entity.User;
import com.journalEntry.journal.Entry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/user"))
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){

        return userService.addUser(user);
    }

    @PutMapping("/updateUser/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName,@RequestBody User user){
        return userService.updateUser(user,userName);
    }
}
