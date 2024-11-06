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

    public ResponseEntity<?> addUserJournalEntity(String userName, JournalEntity entity){
       User user = userRepo.findByUserName(userName);
        if(user!= null){
           JournalEntity journalEntity = journalRepository.save(entity);
            user.getJournalEntityList().add(journalEntity);
            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else return new ResponseEntity(HttpStatus.NO_CONTENT);
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
//            User user = userRepo.findByUserName(userName);
//           List<JournalEntity> list= user.getJournalEntityList();
//           for(JournalEntity entity : list){
//               if(entity.getId().equals(id)){
//                   entity.setTitle(newEntity.getTitle());
//                   entity.setContent(newEntity.getContent());
//                   journalRepository.save(entity);
//               }
//           }
//           user.setJournalEntityList(list);
//           userRepo.save(user);
//           return new ResponseEntity<>(HttpStatus.CREATED);
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
