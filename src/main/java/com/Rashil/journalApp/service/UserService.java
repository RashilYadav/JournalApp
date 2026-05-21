package com.Rashil.journalApp.service;

import com.Rashil.journalApp.entity.User;
import com.Rashil.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;// slf4j(Simple Logging Facade for Java) -> logging abstraction framework
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // logger ka instance banega isliye private kr diya
    // jis class ko use kr rhe honnge wahi class pass karenge getLogger function m
    // harr ek logger ek specific class se associated hota hai isliye humne "JavaEntryService" wali class dedi hai
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // Yeh method:
//    NEW user create karne ke liye hai
//    password ko BCrypt se encode karti hai
//    roles set karti hai
    public boolean saveNewUser(User user) {
        // jo password aayega usko encode krke wapas se user m set kr dijiye
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER")); // hum hardcode kr de rhe ke jo bhi naya user aayega..uska role hum user set kr denge
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.error("error occurred for {}: ", user.getUserName(), e); // {} placeholder hai...isme yeh print hoga -> user.getUserName()
            logger.warn("hahahhaha");
            logger.info("hahahhaha");
            logger.debug("hahahhaha");
            logger.trace("hahahhaha");
            return false;
        }
    }

//    Yeh:
//    sirf existing object save/update karta hai
//    password ko dobara encode nahi karta
//    roles ko reset nahi karta
    public void saveUser(User user) {

            userRepository.save(user);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN")); // role user ke sath admin bhi de diya
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}