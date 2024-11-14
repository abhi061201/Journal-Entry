package com.journalEntry.journal.Entry.controller;

import com.journalEntry.journal.Entry.Entity.JournalEntity;
import com.journalEntry.journal.Entry.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userJournal")
public class UserJournalController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return userService.getUserJournalEntity();
    }

    @PostMapping("/addEntity")
    public ResponseEntity<?> addEntity( @RequestBody JournalEntity entity){
            return userService.addUserJournalEntity( entity);
    }

    @DeleteMapping("/deleteEntity/{userName}/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable String userName, @PathVariable ObjectId id){
        return userService.deleteUserJournalEntity(userName , id);
    }
    @PutMapping("/updateEntity/{userName}/{id}")
    public ResponseEntity<?> updateUserJournalEntity(@PathVariable String userName, @PathVariable ObjectId id,@RequestBody JournalEntity entity){
        return userService.updateUserJournalEntity(userName, id, entity);
    }

    @GetMapping("/getEntity/{id}")
    public ResponseEntity<?> getUserJournalEntityById(@PathVariable ObjectId id){
        return userService.getUserJournalEntityById(id);
    }
    @DeleteMapping("/deleteEntity/{id}")
    public ResponseEntity<?> deleteUserJournalEntityById(@PathVariable ObjectId id){
        return userService.deleteUserJournalEntityById(id);
    }
}
