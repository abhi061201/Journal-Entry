package com.journalEntry.journal.Entry.controller;

import com.journalEntry.journal.Entry.Entity.JournalEntity;
import com.journalEntry.journal.Entry.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<JournalEntity> getEntityById(@PathVariable String id){
        return journalService.getEntityById(id);
    }

    @DeleteMapping("/deleteEntity")
    public ResponseEntity<Void> deleteEntity(){
        return journalService.clear();
    }
    @DeleteMapping("/deleteEntity/id/{id}")
    public ResponseEntity<JournalEntity> deleteEntityById(@PathVariable String id){
        return journalService.deleteById(id);
    }


    @PutMapping("/updateEntity/id/{id}")
    public ResponseEntity<JournalEntity> updateEntityById(@PathVariable String id, @RequestBody JournalEntity entity){
        return journalService.updateById(id,entity);
    }
}
