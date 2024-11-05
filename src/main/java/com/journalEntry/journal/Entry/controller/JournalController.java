package com.journalEntry.journal.Entry.controller;

import com.journalEntry.journal.Entry.Entity.JournalEntity;
import com.journalEntry.journal.Entry.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JournalController {
    List<JournalEntity> list = new ArrayList<>();

    @Autowired
    JournalService journalService;
    @GetMapping("/getEntity")
    public ResponseEntity<List<JournalEntity> > getEntity(){
        return journalService.getEntity();

    }



    @PostMapping("/addEntity")
    public ResponseEntity<JournalEntity> addEntity(@RequestBody JournalEntity entity)
    {
        return journalService.addEntity(entity);

    }
    @GetMapping("/getEntity/id/{id}")
    public ResponseEntity<JournalEntity> getEntityById(@PathVariable ObjectId id){
        return journalService.getEntityById(id);
    }

    @DeleteMapping("/deleteEntity")
    public ResponseEntity<Void> deleteEntity(){
        return journalService.clear();
    }
    @DeleteMapping("/deleteEntity/id/{id}")
    public ResponseEntity<JournalEntity> deleteEntityById(@PathVariable ObjectId id){
        return journalService.deleteById(id);
    }


    @PutMapping("/updateEntity/id/{id}")
    public ResponseEntity<JournalEntity> updateEntityById(@PathVariable ObjectId id, @RequestBody JournalEntity entity){
        return journalService.updateById(id,entity);
    }
}
