package com.example.hibernatesearchhangissueproof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;

@SpringBootApplication
//@EnableJpaRepositories // WORKAROUND #2
//@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFERRED) // WORKAROUND #3
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
