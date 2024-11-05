package com.journalEntry.journal.Entry.repository;

import com.journalEntry.journal.Entry.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);
}
