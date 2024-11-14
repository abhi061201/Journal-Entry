package com.journalEntry.journal.Entry.controller;

import com.journalEntry.journal.Entry.Entity.User;
import com.journalEntry.journal.Entry.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;
    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody User user){
       return  adminService.addUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteAdmin(@RequestBody User user){

    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/allAdmin")
    public ResponseEntity<?> getAllAdmin(){
        return null;
    }
}
