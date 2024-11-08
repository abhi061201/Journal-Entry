package com.journalEntry.journal.Entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalEntryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalEntryApplication.class, args);
	}


	// MongoDatabaseFactory dbFactory -> higher level p ye db sessions manage krta h. and usi ko Tranactional context bolte h
	// ye manager h ...saare transaction ko closely montitor krta h ...commit and rollback krne k liye
	// ye function ka naam hum koi bhi rkh skte h spring apne aap dhoodh leta h k konsi bean manager ko
	// implement krr rhi h
	@Bean
	public PlatformTransactionManager add (MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}
}
