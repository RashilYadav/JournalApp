package com.Rashil.journalApp.entity;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

// iss data ko map krna padega humko uss collection se jo ki DB ke andar hoga isliye
// @Document likhenge
@Document(collection = "journal_entries")
@Data // getter, setter sab generate krta hai..alag se setter, getter annotation use krne ki need nhi hai
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id; // id ko primary key banana tha isliye @Id annotation likha hai...ObjectId MongoDB m id ka type hai ..toh jo waha hai wahi yaha le liya
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getContent() {
//        return content;
//    }
}