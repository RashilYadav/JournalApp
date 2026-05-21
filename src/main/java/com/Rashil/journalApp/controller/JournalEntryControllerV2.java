package com.Rashil.journalApp.controller;

import com.Rashil.journalApp.entity.JournalEntry;
import com.Rashil.journalApp.entity.User;
import com.Rashil.journalApp.service.JavaEntryService;
import com.Rashil.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal") // yeh hamari poori class ki mapping kr deta hai
public class JournalEntryControllerV2 {
    @Autowired
    private JavaEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping // pehle "/userName" diya the mapping m ab hta diya..."SecurityContextHolder" use kr rhe toh
    // yeh wala jo code hai users collection ke sath sync nhi hai...unn dono ko sync krne ke liye
    // update kr rhe hain code
//    public ResponseEntity<?> getAll() {
//        List<JournalEntry> all = journalEntryService.getAll();
//        if(all != null && !all.isEmpty()) {
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    public ResponseEntity<?> getAllJournalEntriesOfUser() { // pehle as a parameter pass kr rhe the hum @PathVariable String userName ko function m but ab since hum SecurityContextHolder use kr rhe toh humko postman se hi authenticate krke username mil jayega toh alag se likhne ki need nhi hai
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
//        List<JournalEntry> all = journalEntryService.getAll();
        List<JournalEntry> all = user.getJournalEntries(); // particular user ki saari ki saari journal entries aa jayengi
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
//    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
//        try {
//        journalEntryService.saveEntry(myEntry); // jo data POST ke through humko postman m milega usko bas hum save kr lenge
//        return new ResponseEntity<>(HttpStatus.CREATED);
//        }catch(Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(myEntry, userName); // jo data POST ke through humko postman m milega usko bas hum save kr lenge
            return new ResponseEntity<>( myEntry, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    // agar GET krte hue humne galat id dedi toh hum chahenge ki status code 200 OK ki jagah error bataye
    // esa hum ResponseEntity ke through karenge..agar galat id hogi toh Not_Found ka error denge
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
       // return journalEntryService.findById(myId).orElse(null); // old concept
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);// pehle user nikala
        // fir jo user nikala uski jitni bhi journalEntries hain..usme hum find kr rhe ki myId hai ke nhi hai
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        // agar id mil jaati hai toh uski shakal de dijiye hume
        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId); // optional ka mtlb hai mile ya na mile
            if(journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // NOT FOUND return karega jab hum postman m test kr rhe honge yeh endpoint..kyuki hum khud hi return NOT FOUND kara rhe hain
    }

    @DeleteMapping("id/{myId}")
    // ? -> wildcard pattern hai...zaruri nhi hai ki entity class hi de ..hum kisi aur class ka object bhi
    // return kr skte hain
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = journalEntryService.deleteById(myId, userName);
        if(removed) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateJournalById(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntry newEntry
            ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myId); // optional ka mtlb hai mile ya na mile
            if(journalEntry.isPresent()) {
                JournalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old); // saveEntry function toh user expect kr rha hai pr humko user pass krne ki need nhi hai kyuki userName ke through content
                // update ho raha hai isliye hum doosre function bana dete hai
                // do function hain ab saveEntry naam ke
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}