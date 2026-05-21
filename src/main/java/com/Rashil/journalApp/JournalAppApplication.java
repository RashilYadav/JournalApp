package com.Rashil.journalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalAppApplication {

	public static void main(String[] args) {

	 	ConfigurableApplicationContext context = SpringApplication.run(JournalAppApplication.class, args);
		ConfigurableEnvironment environment = context.getEnvironment();
		System.out.println(environment.getActiveProfiles()[0]); // isse yeh print kara rhe ki kaunsi profile active hai..ddev ya prod
	}

	@Bean
	// MongoDatabaseFactory -> database connection setup krne ke liye hota hai
	// isi se sessions bante hain
	// Bean implement kr rhi hai "PlatformTransactionManager" ko
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
}


// yeh dono kaam karenge commit aur rollback krne ka
// PlatformTransactionManager -> interface hai, yeh transactions ko manage karega
// MongoTtransactionManager
