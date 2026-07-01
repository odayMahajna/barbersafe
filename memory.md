# Barbersafe Project Progress - Local Development Setup

## 1. Technology Stack Summary
- **Language**: Java 21
- **Framework**: Spring Boot (Parent v4.1.0)
- **Database**: PostgreSQL (Local/Docker)
- **ORM/JPA**: Hibernate / Spring Data JPA
- **Dependencies Loaded**: 
  - `spring-boot-starter-webmvc`
  - `postgresql` (Runtime scope)
  - `spring-boot-starter-data-jpa`

## 2. Database Schema (`barbersafe_db`)
*Based on your provided SQL schema.*

### Tables:
1. **users**
   - Columns: `id`, `email`, `password_hash`, `role` ('BARBER', 'GUEST'), `created_at`
   
2. **services**
   - Columns: `id`, `barber_id` (FK), `service_name`, `duration_minutes`, `price`

3. **appointments**
   - Columns: `id`, `guest_id` (FK, nullable for walk-ins?), `barber_id` (FK with CHECK constraint on role='BARBER'), `scheduled_time`, `status` ('CONFIRMED', 'CANCELLED'), `paid_status`

- **Triggers**: 
  - Function `ensure_availability()` prevents double-booking within a specific time window.
  - Trigger `prevent_double_book` fires before INSERT to reject conflicts.

## 3. Entity Classes (TBD)
*You will need to create these entities in the package structure matching your POM.*

Assuming package: `com.barbersafe.model.entity` or similar, you likely have classes like:
- `UserEntity`: Implements JPA User with custom getters/setters for role filtering logic.
- `ServiceEntity`: Links `BarberEntity`.
- `AppointmentEntity`: Links `Guest/User Entity`, has constraints on time/status.

## 4. Repository Layer (IMPLEMENTED)
*Package: Likely `com.barbersafe.repository` or `repository` subpackage.*

### Interfaces Created/Planned:
1. **UserRepository** (`extends JpaRepository<UserEntity, Long>`)
   - Method: `findByRole(String roleName)` -> Returns List of Users filtered by role (e.g., "BARBER").
   - Method: `existsByEmailAndRole(email, role)` -> Boolean check for login validation combined with authorization context.

2. **ServiceRepository** (`extends JpaRepository<ServiceEntity, Long>`)
   - Method: `findByBarberId(Long barberId)` -> Returns List of services available from a specific Barber ID.
   
3. **AppointmentRepository** (`extends JpaRepository<AppointmentEntity, Long>`)
   - Note: Standard JPA queries may require Spring Data query methods (e.g., `@Query("SELECT ... FROM Appointment WHERE scheduled_time >= :now AND status != 'CANCELLED'")`).
   - Logic Check Method: `hasConflictingAppointment(LocalDateTime timeSlot, Long barberId)` -> Checks for existing non-cancelled slots in the same minute.

## 5. Next Steps
- [ ] Create Entity Classes matching SQL schema exactly (including foreign keys).
- [ ] Configure `application.properties` with local DB credentials (`jdbc:postgresql://localhost:5432/barbersafe_db`).
- [ ] Implement Service Layer for business logic (Booking validation, Payment handling).
- [ ] Build REST Controllers to expose booking endpoints.

## 6. Notes on Concurrency & Safety
- **Primary Protection**: The SQL Trigger `ensure_availability()` is the main safety net against double bookings even during high traffic or concurrent insertions in PostgreSQL.
- **Secondary Protection**: The repository method `hasConflictingAppointment` adds a Java-level sanity check before executing the DB transaction, ensuring the logic holds up if triggers fail unexpectedly (though highly unlikely).

---
*Last Updated: [Current Date]*
