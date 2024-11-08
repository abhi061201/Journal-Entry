package com.journalEntry.journal.Entry.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    ObjectId id;

    @Indexed(unique = true) // need to add in application.properties
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @DBRef // this annotation will give ref for JournalEntityList
    // now userJournalEntityList will store reference of JournalEntity Object.
    //since we have JournalEntity already so no need to redundant data
    private List<JournalEntity> journalEntityList = new ArrayList<>();


    //part of Spring Security Authorization
    private List<String> role;

}
