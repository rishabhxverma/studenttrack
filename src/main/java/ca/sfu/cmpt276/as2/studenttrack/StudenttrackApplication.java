package ca.sfu.cmpt276.as2.studenttrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudenttrackApplication {
	//TODO: in application properties, change url to internal url found on render dashboard
	public static void main(String[] args) {
		SpringApplication.run(StudenttrackApplication.class, args);
	}

}
