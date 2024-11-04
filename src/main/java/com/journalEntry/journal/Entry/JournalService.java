package com.journalEntry.journal.Entry;

import com.journalEntry.journal.Entry.Entity.JournalEntity;
import com.journalEntry.journal.Entry.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    JournalRepository journalRepository;

    public ResponseEntity<List<JournalEntity>> getEntity(){
        try{
            return new ResponseEntity(journalRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<JournalEntity> getEntityById(String id){
        try{
            Optional<JournalEntity> entity = journalRepository.findById(id);
            return entity.isPresent()?new ResponseEntity(entity.get(),HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<JournalEntity> addEntity(JournalEntity entity){
        try{
            journalRepository.save(entity);
            return new ResponseEntity(entity,HttpStatus.CREATED);
        }
        catch (Exception e){

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Void> clear(){
        journalRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<JournalEntity> deleteById(String id){
        Optional<JournalEntity> entity = journalRepository.findById(id);
        if(entity.isPresent()){
            journalRepository.deleteById(id);
            return new ResponseEntity<>(entity.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    public ResponseEntity<JournalEntity> updateById(String id, JournalEntity entity){
        Optional<JournalEntity> oldEntity = journalRepository.findById(id);
        if(oldEntity.isPresent()){
            oldEntity.get().setContent(entity.getContent());
            oldEntity.get().setTitle(entity.getTitle());
            journalRepository.save(oldEntity.get());
            return new ResponseEntity<>(oldEntity.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
