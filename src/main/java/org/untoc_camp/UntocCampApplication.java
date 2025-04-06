package org.untoc_camp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UntocCampApplication {

	public static void main(String[] args) {
		SpringApplication.run(UntocCampApplication.class, args);
	}

}
