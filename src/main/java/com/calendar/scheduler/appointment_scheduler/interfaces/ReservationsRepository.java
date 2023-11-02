package com.calendar.scheduler.appointment_scheduler.interfaces;
import com.calendar.scheduler.appointment_scheduler.models.Availabilities;
import com.calendar.scheduler.appointment_scheduler.models.Reservations;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.query.Param;

public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Reservations r WHERE r.startTime = :startTime AND r.endTime = :endTime AND r.email = :email")
    void deleteReservation(@Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime,
                              @Param("email") String email);


    List<Reservations> findByEmail(String email);

}