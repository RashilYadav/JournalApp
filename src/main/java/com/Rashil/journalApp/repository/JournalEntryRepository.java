package com.Rashil.journalApp.repository;

import com.Rashil.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

// jispe operation kr rhe woh pass kiya...hum dhoondh rhe hain journalEntry aur id ka type hai
// ObjectId isliye ObjectId pass kiya ...JournalEntry wali file m jo id variable hai woh ObjectId type ka hai
// isliye yaha bhi uska datatype String pass kiya hai
// ispe @Component nhi likha hai bhale hai iska object banaya hai @Autowired ke through kyuki yeh eek interface hai
// aur spring automatically detect aur generate krta hai implementation at runtime
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
