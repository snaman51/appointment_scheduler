package com.calendar.scheduler.appointment_scheduler.controllers;
import java.util.List;
import com.calendar.scheduler.appointment_scheduler.interfaces.ReservationsRepository;
import com.calendar.scheduler.appointment_scheduler.models.Reservations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.calendar.scheduler.appointment_scheduler.interfaces.AvailabilitiesRepository;
import com.calendar.scheduler.appointment_scheduler.models.Availabilities;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path="/schedule")
public class AvailabilitiesController {
    @Autowired
    private AvailabilitiesRepository availabilityRepo;

    @Autowired
    private ReservationsRepository reservationRepo;

    @Operation(summary = "Get list of Availabilities", description = "Retrieve the list of Availabilities.")
    @GetMapping("/listavailabilities")
    public ResponseEntity<List<Availabilities>> listAvailabilities() {
        try {
            return new ResponseEntity<>(availabilityRepo.findByStatusFalse(), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("Failed to retrieve availabilities: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create an Availability", description = "To create a new Availability.")
    @PostMapping("/createavailability")
    public ResponseEntity<String> createAvailability(@RequestBody Availabilities availability) {
        try {
            Availabilities createdAvailability = availabilityRepo.save(availability);
            return ResponseEntity.ok("Received availability: " + createdAvailability);
        }
        catch (Exception e) {
            System.out.println("Failed to create availability: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get list of Reservations", description = "Retrieve the list of Reservations.")
    @GetMapping("/listreservations")
    public ResponseEntity<List<Reservations>> listReservations() {
        try {
            return new ResponseEntity<>(reservationRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("Failed to retrieve reservations: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get list of Reservations", description = "Retrieve the list of Reservations.")
    @GetMapping("/reservationsbyemail")
    public ResponseEntity<List<Reservations>> listReservationsByEmail(@RequestParam String email) {
        return new ResponseEntity<>(reservationRepo.findByEmail(email), HttpStatus.OK);
    }

    @Operation(summary = "Create a Reservation", description = "To create a new Reservation. When a new reservation is created, the slot from availabilities is deleted.")
    @PostMapping("/createreservation")
    public ResponseEntity<String> createReservation(@RequestBody Reservations reservation) {
        try {
            Reservations createdReservation = reservationRepo.save(reservation);
            availabilityRepo.updateStatusToTrue(reservation.getStartTime(), reservation.getEndTime());
            return ResponseEntity.ok("Received availability: " + createdReservation);
        }
        catch (Exception e) {
            System.out.println("Failed to create reservation: " + e.getMessage());
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete a Reservation", description = "To delete a reservation.")
    @DeleteMapping("/deletereservation")
    public void deleteReservation(@RequestBody Reservations reservation) {
        try {
            availabilityRepo.updateStatusToFalse(reservation.getStartTime(), reservation.getEndTime());
            reservationRepo.deleteReservation(reservation.getStartTime(), reservation.getEndTime(), reservation.getEmail());
        }
        catch (Exception e) {
            System.out.println("Failed to delete reservation: " + e.getMessage());
        }
        }

    @Operation(summary = "Delete an Availability", description = "To delete an Availabilty.")
    @DeleteMapping("/deleteavailability")
    public void deleteAppointment(@RequestBody Availabilities availability) {
        try {
            availabilityRepo.deleteAvailability(availability.getStartTime(), availability.getEndTime());
        }
        catch (Exception e) {
            System.out.println("Failed to delete availability: " + e.getMessage());
        }
    }
       
}