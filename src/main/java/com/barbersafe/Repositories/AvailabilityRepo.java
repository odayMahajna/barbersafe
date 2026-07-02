package com.barbersafe.Repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.barbersafe.models.Availability;

public interface AvailabilityRepo extends JpaRepository<Availability, UUID> {

     List<Availability> findByBarberIdAndDayOfWeek(UUID barberId, Short dayOfWeek);

     List<Availability> findByBarberId(UUID barberId);

}