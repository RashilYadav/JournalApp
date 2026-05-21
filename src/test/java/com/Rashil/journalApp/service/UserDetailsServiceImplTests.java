//package com.Rashil.journalApp.service;
//
//import com.Rashil.journalApp.entity.User;
//import com.Rashil.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import java.util.ArrayList;
//
//import static org.mockito.Mockito.*;
//
////@SpringBootTest
//public class UserDetailsServiceImplTests {
////    @MockitoBean
////    private UserRepository userRepository; // UserRepository ko mock kr rhe taaki ek "fake" user aa sake
//
////    @Autowired // yeh tabhi chalega jab @SpringBootTest annotation likha hoga
////    private UserDetailsServiceImpl userDetailsService;
//
//    @InjectMocks // yeh inject kr lega classes
//    private UserDetailsServiceImpl userDetailsService;
//    @Mock
//    private UserRepository userRepository; // UserRepository ko mock kr rhe taaki ek "fake" user aa sake
//
//    @BeforeEach
//    // yeh nahi krenge toh run krne pr error dega aur userRepository ko null bta dega
//    // isse userRespository initialize kr rhe..null exception ni dega
//
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void loadUserByUsernameTest() {
//        // isse actual user na call hoke mock chalega
//        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("wsffsf").roles(new ArrayList<>()).build()); // jab jo call kiya uske badle return karega jo ki thenReturn ke andar likha hai
//        UserDetails user = userDetailsService.loadUserByUsername("ram");
//        Assertions.assertNotNull(user);
//    }
//}