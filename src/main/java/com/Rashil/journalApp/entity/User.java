package com.Rashil.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data // lombok m aata hai yeh annotation
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true) // indexing create hogi...unique usernames milenge humko
    // jab collection create hota hai toh by default usme woh indexed nhi hota..usko indexed banane ke liye
    // humko alag se command chalani padegi..."spring.data.mongodb.auto-index-creation = true" -> yeh line likhi hai applications.properties m to do so
    @NonNull // lombok m aata hai yeh annotation
    private String userName;
    @NonNull
    private String password;

    @DBRef // iss annotation ke through hum "users" collection ke andar reference create kr rhe hain "journal_entries" collection ka
    // yeh annotation ek foreign key ki tarah kaam kr rha hai...link banane ka kaam kr rha hai yeh between the two collections
    // yeh humne khali list initialize ki hai...isliye harr ek user ki entry m humko khali journalEntries naam ki list dikhegi
    private List<JournalEntry> journalEntries = new ArrayList<>(); // @DERef ke through yeh List reference rakhegi unn entries ka jo journal_entries collection m hai
    private List<String> roles; 
}