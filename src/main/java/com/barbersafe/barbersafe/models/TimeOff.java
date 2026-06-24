package com.barbersafe.barbersafe.models;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

/**
 * Maps to the `time_off` table — one-off exceptions to a
 * barber's normal weekly schedule (vacation, sick day, holiday).
 * Unlike AvailabilityRule, this is a concrete date/time range,
 * not a recurring weekly pattern.
 */
@Entity
@Table(name = "time_off")
public class TimeOff {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "id", updatable = false, nullable = false)
     private UUID id;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "barber_id", nullable = false)
     private Barber barber;

     @Column(name = "starts_at", nullable = false)
     private Instant startsAt;

     @Column(name = "ends_at", nullable = false)
     private Instant endsAt;

     @Column(name = "reason", length = 255)
     private String reason;

     public TimeOff() {
     }

     // ----- Getters and setters -----

     public UUID getId() {
          return id;
     }

     public void setId(UUID id) {
          this.id = id;
     }

     public Barber getBarber() {
          return barber;
     }

     public void setBarber(Barber barber) {
          this.barber = barber;
     }

     public Instant getStartsAt() {
          return startsAt;
     }

     public void setStartsAt(Instant startsAt) {
          this.startsAt = startsAt;
     }

     public Instant getEndsAt() {
          return endsAt;
     }

     public void setEndsAt(Instant endsAt) {
          this.endsAt = endsAt;
     }

     public String getReason() {
          return reason;
     }

     public void setReason(String reason) {
          this.reason = reason;
     }
}