package com.Rashil.journalApp.service;

import com.Rashil.journalApp.entity.JournalEntry;
import com.Rashil.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.Rashil.journalApp.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component // yeh wala annotation use kiya kyuki JournalEntryControllerV2 wali file m humne
// @Autowired component ka use krke iska object banaya hai
@Slf4j
public class JavaEntryService {
    @Autowired // iss annotation ke through hum JournalEntryRepository class ko iss class m inject kr rhe hain
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now()); // koi entry aayegi toh curremt time set ho jayega
            JournalEntry saved = journalEntryRepository.save(journalEntry); // jo bhi entry aayegi usko save kr lenge
            user.getJournalEntries().add(saved); // user ki jo journalEntries wali list hai users collection m usme hum "saved" variable se milne wali journal entry daalenge
//            user.setUserName(null);
//            userService.saveNewEntry(user); // ab data hum final save kr denge hamare user collection m
            userService.saveUser(user);
        }
        catch (Exception e) {
            log.error("Exception ", e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
            journalEntryRepository.save(journalEntry);
    }

    // jitni bhi entries hain database m ..unhe hum GET krna chahte hain
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {// hum yeh krna chah rhe hain ki agar users collection m kisi user ka data delete ho toh usse related journalentries bhi delete ho jaye journalentries wale colelction m and vice versa
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id)); // yeh boolean value return karega isliye remove naam ke boolean type variable m save kr liya humne
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting the entry.");
        }
        return removed;
    }
//
//    public List<JournalEntry> findByUserName(String userName) {
//
//    }
}