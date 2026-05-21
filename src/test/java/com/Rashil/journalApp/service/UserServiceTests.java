//package com.Rashil.journalApp.service;
//
//import com.Rashil.journalApp.entity.User;
//import com.Rashil.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvFileSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTests {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Disabled
//    // agar kuch cheez initialize krni hai before running each test toh iss annotation ka use kare
//    @BeforeEach
////    @BeforeAll -> agar hazaar test likhe ho toh bhi unke run hone se pehle yeh run karega
//    void setUp(){
//
//    }
//
////    @AfterEach -> kuch test ke khtm hone k baad yeh wala chalega
////    @AfterAll -> saare test hone ke baad yeh wala chalega
//
////    @Disabled // used to disable test for testFindByUserName method
////    @Test
////    public void testAdd() {
////        assertEquals(4, 2+1); // test fail ho jayega
////    }
//
////    @ParameterizedTest
////    @CsvSource({
////            "ram",
////            "Rashil",
////            "Anish",
////            "vipul"
////    })
////    public void testFindByUserName(String name) {
//////        assertEquals();
//////        User user = userRepository.findByUserName("Sara");
//////        assertTrue(!user.getJournalEntries().isEmpty());
////        assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
////    }
//
////    @ParameterizedTest
////    @ValueSource(strings = {
////            "ram",
////            "Rashil",
////            "Anish",
////            "vipul"
////    })
////    public void testFindByUserName(String name) {
//////        assertEquals();
//////        User user = userRepository.findByUserName("Sara");
//////        assertTrue(!user.getJournalEntries().isEmpty());
////        assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
////    }
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class) // UserArgumentProvider naam ki class banayi iske liye
//    // iss test se naye user create ho jayenge
//    // agar postman m "localhost:8081/journal/admin/all-users -> iss API se user get karoge toh aa jayenge saare
//    // jo naye create hue woh bhi aa jayenge
//    public void testSaveNewUser(User user) {
////        assertEquals();
////        User user = userRepository.findByUserName("Sara");
////        assertTrue(!user.getJournalEntries().isEmpty());
//        assertTrue(userService.saveNewUser(user));
//    }
//
//    // agar method m parameters dene hai toh @ParameterizedTest krke annotation use krenge
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1, 1, 2", // represents int a, int b, int expected variables
//            "2, 10, 12",
//            "3, 3, 9"
//    })
//    public void test(int a, int b, int expected) {
//        assertEquals(expected, a+b);
//    }
//}
