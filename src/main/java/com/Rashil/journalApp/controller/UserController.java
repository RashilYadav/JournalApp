package com.Rashil.journalApp.controller;
import com.Rashil.journalApp.entity.User;
import com.Rashil.journalApp.repository.UserRepository;
import com.Rashil.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // yeh update aur delete wale endpoints hain..jo hum access kr payenge keval login krne pr
    // humne pehle {/username} krke mapping di thi yaha ...@PutMapping ke pass..but authentication lagayi hai toh hata diya
    @PutMapping // hum content update id ke through nhi kr rhe..rather username ke through
    // like database m check karenge ki iss naam ka user hai kya..
    // iske liye findByUserName naam ki field bhi banayi UserRepository file m
    // yeh hum journalEntry m nhi kr paa rhe the kyuki Indexing use nhi ho rhi thi usme
    public ResponseEntity<?> updateUser(@RequestBody User user) {
//        User userInDB = userService.findByUserName(user.getUserName()); // pathvariable se get nhi kr rhe the iss line m username
        // jab koi user authenticate hota hai toh uska data store hota hai "SecurityContextHolder" ke andar
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName(); // jab user authenticate ho chuka hoga...tabhi uska naam aayega
        User userInDB = userService.findByUserName(userName); // pathvariable ke through user get karenge fir update karenge
        // RequestBody se naya ya purana jo bhi password ya username aayega ..usko set kr denge
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());

        userService.saveNewUser(userInDB); // iske sath id bhi aayegi mtlb same id pe data save ho jayega
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsersById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}