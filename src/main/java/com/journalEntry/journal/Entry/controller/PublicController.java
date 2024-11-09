package com.journalEntry.journal.Entry.controller;

import com.journalEntry.journal.Entry.Entity.User;
import com.journalEntry.journal.Entry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("healthCheck")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userService.addUserSecured(user);
    }


}
