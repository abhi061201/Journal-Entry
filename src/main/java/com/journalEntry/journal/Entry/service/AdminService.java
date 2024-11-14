package com.journalEntry.journal.Entry.service;


import com.journalEntry.journal.Entry.Entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class AdminService {

    @Autowired UserService userService;
    public ResponseEntity<?> getAllUsers(){
        List<User> list= userService.getAllUser();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addUser(User user){
         try{

             userService.addAdmin(user);
             return new ResponseEntity<>(HttpStatus.CREATED);
         }
         catch(Exception e){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

}
