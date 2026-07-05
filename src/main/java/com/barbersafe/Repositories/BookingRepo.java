package com.barbersafe.Repositories;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barbersafe.models.Booking;

public interface BookingRepo extends JpaRepository<Booking, UUID> {

     //returns all bookings in time range.
     List<Booking> findByBarberIdAndStartsAtBetween(UUID barberId, Instant startTime, Instant endTime);

     // returns all booking that overlap.
     @Query(value = """
               SELECT * FROM bookings b
               WHERE b.barber_id = :barberId
                 AND b.status <> 'cancelled'
                 AND ((b.starts_at < :endsAt AND b.ends_at > :startsAt))""",
                  nativeQuery = true)
     List<Booking> findOverlapping(UUID barberId, Instant startsAt, Instant endsAt);
}
