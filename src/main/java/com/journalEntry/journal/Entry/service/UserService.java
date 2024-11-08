package com.journalEntry.journal.Entry.service;

import com.journalEntry.journal.Entry.Entity.JournalEntity;
import com.journalEntry.journal.Entry.Entity.User;
import com.journalEntry.journal.Entry.repository.JournalRepository;
import com.journalEntry.journal.Entry.repository.UserRepo;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JournalRepository journalRepository;



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

//    public User addUserSecured(User user){
//
//    }

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

    @Transactional
    public ResponseEntity<?> addUserJournalEntity(String userName, JournalEntity entity){
       try
       {
           User user = userRepo.findByUserName(userName);
           if(user!= null){
               JournalEntity journalEntity = journalRepository.save(entity);
               user.getJournalEntityList().add(journalEntity);
               userRepo.save(user);
               return new ResponseEntity<>(HttpStatus.CREATED);
           }
           else return new ResponseEntity(HttpStatus.NO_CONTENT);
       }
       catch(Exception e){
           System.out.println("Api Exception"+e);
           throw new RuntimeException("Something had gone wrong "+e);
       }
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


    public ResponseEntity<?> deleteUserJournalEntity(String userName, ObjectId id){
        try{
            User user = userRepo.findByUserName(userName);

            user.getJournalEntityList().removeIf(x-> x.getId() == id);
            journalRepository.deleteById(id);

            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.FOUND);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<?> updateUserJournalEntity(String userName,ObjectId id, JournalEntity newEntity){
        try{
            //just need to update journal entry...the username journal entry will be updated automatically because it is just reference

            JournalEntity oldEntity = journalRepository.findById(id).orElse(null);
            if(oldEntity!=null){
                oldEntity.setContent(newEntity.getContent());
                oldEntity.setTitle(newEntity.getTitle());
            }
            journalRepository.save(oldEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
