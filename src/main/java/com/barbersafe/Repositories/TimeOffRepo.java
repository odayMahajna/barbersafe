package com.barbersafe.Repositories;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.barbersafe.models.TimeOff;

public interface TimeOffRepo extends JpaRepository<TimeOff, UUID> {

     List<TimeOff> findByBarberId(UUID barberId);
     
     @Query(value = """
               SELECT * FROM time_off t
               WHERE t.barber_id = :barberId
                 AND t.starts_at < :rangeEnd
                 AND t.ends_at > :rangeStart
               """, nativeQuery = true)
     List<TimeOff> findOverlapping(
          @Param("barberId") UUID barberId,
          @Param("rangeStart") Instant rangeStart,
          @Param("rangeEnd") Instant rangeEnd);
}
