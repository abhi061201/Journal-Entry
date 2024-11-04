package com.journalEntry.journal.Entry.repository;

import com.journalEntry.journal.Entry.Entity.JournalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface JournalRepository extends MongoRepository<JournalEntity,String> {
}
