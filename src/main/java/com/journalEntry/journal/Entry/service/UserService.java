package com.journalEntry.journal.Entry.service;

import com.journalEntry.journal.Entry.Entity.JournalEntity;
import com.journalEntry.journal.Entry.Entity.User;
import com.journalEntry.journal.Entry.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public User findUserByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

    public User addUser(User user){
        userRepo.save(user);
        return user;
    }

    public User findById(ObjectId id){
        return userRepo.findById(id).orElse(null);
    }

    public User deleteById(ObjectId id){
        User user = userRepo.findById(id).orElse(null);
        if(user!=null){
            userRepo.deleteById(id);
        }
        return user;
    }


    public List<JournalEntity> getUserJournalEntity(String userName){

        User user = userRepo.findByUserName(userName);
        return user.getJournalEntityList();
    }

    public ResponseEntity<?> updateUser(User newUser, String userName){
        User user = userRepo.findByUserName(userName);
        if(user!=null){
            user.setUserName(newUser.getUserName());
            user.setPassword(newUser.getPassword());
            userRepo.save(user);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
