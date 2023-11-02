package com.calendar.scheduler.appointment_scheduler.interfaces;
import org.springframework.data.jpa.repository.JpaRepository;
import com.calendar.scheduler.appointment_scheduler.models.Availabilities;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.time.LocalDateTime;


public interface AvailabilitiesRepository extends JpaRepository<Availabilities, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Availabilities r WHERE r.startTime = :startTime AND r.endTime = :endTime")
    void deleteAvailability(@Param("startTime") LocalDateTime startTime,
                           @Param("endTime") LocalDateTime endTime);

    List<Availabilities> findByStatusFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Availabilities a SET a.status = true WHERE " +
            "(a.startTime <= :startTime AND a.startTime <= :endTime) " +
            "AND (a.endTime >= :startTime AND a.endTime >= :endTime)")
    void updateStatusToTrue(@Param("startTime") LocalDateTime startTime,
                               @Param("endTime") LocalDateTime endTime);
    @Modifying
    @Transactional
    @Query("UPDATE Availabilities a SET a.status = false WHERE " +
            "(a.startTime <= :startTime AND a.startTime <= :endTime) " +
            "AND (a.endTime >= :startTime AND a.endTime >= :endTime)")
    void updateStatusToFalse(@Param("startTime") LocalDateTime startTime,
                            @Param("endTime") LocalDateTime endTime);
}
