package com.barbersafe.models;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
     // the only allowed status.
     public enum BookingStatus {
          pending,
          confirmed,
          cancelled,
          completed,
          no_show
     }

     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "id", updatable = false, nullable = false)
     private UUID id;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "barber_id", nullable = false)
     private Barber barber;

     @Column(name = "guest_name", nullable = false, length = 120)
     private String guestName;

     @Column(name = "guest_phone", nullable = false, length = 30)
     private String guestPhone;

     @Column(name = "guest_email", length = 255)
     private String guestEmail;

     @Column(name = "starts_at", nullable = false)
     private Instant startsAt;

     @Column(name = "ends_at", nullable = false)
     private Instant endsAt;

     @Enumerated(EnumType.STRING)
     @Column(name = "status", nullable = false)
     private BookingStatus status = BookingStatus.pending;

     @Column(name = "notes")
     private String notes;

     @Column(name = "created_at", nullable = false, updatable = false)
     private Instant createdAt;

     @Column(name = "updated_at", nullable = false)
     private Instant updatedAt;

}
