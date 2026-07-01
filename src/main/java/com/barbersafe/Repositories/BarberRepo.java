package com.barbersafe.Repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.barbersafe.models.Barber;

public interface BarberRepo extends JpaRepository<Barber, UUID> {
     Optional<Barber> findByEmail(String email);
}
