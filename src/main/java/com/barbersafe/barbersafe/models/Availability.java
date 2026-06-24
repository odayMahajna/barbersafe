package com.barbersafe.barbersafe.models;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Maps to the `availability_rules` table — a barber's recurring
 * weekly working hours, e.g. "Monday 09:00–17:00". A barber
 * typically has several rows, one per working block.
 */
@Entity
@Table(name = "availability")
public class Availability {

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "id", updatable = false, nullable = false)
     private UUID id;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "barber_id", nullable = false)
     private Barber barber;

     /**
      * 0 = Sunday ... 6 = Saturday, matching the CHECK constraint
      * (day_of_week BETWEEN 0 AND 6) in the database.
      */
     @Column(name = "day_of_week", nullable = false)
     private Short dayOfWeek;

     @Column(name = "start_time", nullable = false)
     private LocalTime startTime;

     @Column(name = "end_time", nullable = false)
     private LocalTime endTime;

     public Availability() {
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

     public Short getDayOfWeek() {
          return dayOfWeek;
     }

     public void setDayOfWeek(Short dayOfWeek) {
          this.dayOfWeek = dayOfWeek;
     }

     public LocalTime getStartTime() {
          return startTime;
     }

     public void setStartTime(LocalTime startTime) {
          this.startTime = startTime;
     }

     public LocalTime getEndTime() {
          return endTime;
     }

     public void setEndTime(LocalTime endTime) {
          this.endTime = endTime;
     }
}