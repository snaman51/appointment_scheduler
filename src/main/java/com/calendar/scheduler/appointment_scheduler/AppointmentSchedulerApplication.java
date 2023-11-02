package com.calendar.scheduler.appointment_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


//@SpringBootApplication
//@EntityScan(basePackages = "com.calendar.scheduler.appointment_scheduler.models")
//public class AppointmentSchedulerApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(AppointmentSchedulerApplication.class, args);
//	}
//
//}
@SpringBootApplication
@EntityScan(basePackages = "com.calendar.scheduler.appointment_scheduler.models")
public class AppointmentSchedulerApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppointmentSchedulerApplication.class, args);
	}
}

