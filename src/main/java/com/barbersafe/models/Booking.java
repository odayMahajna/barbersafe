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
          PENDING,
          CONFIRMED,
          CANCELLED,
          COMPLETED,
          NO_SHOW
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
     private BookingStatus status = BookingStatus.PENDING;

     @Column(name = "notes")
     private String notes;

     @Column(name = "created_at", nullable = false, updatable = false)
     private Instant createdAt;

     @Column(name = "updated_at", nullable = false)
     private Instant updatedAt;

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

     public String getGuestName() {
          return guestName;
     }

     public void setGuestName(String guestName) {
          this.guestName = guestName;
     }

     public String getGuestPhone() {
          return guestPhone;
     }

     public void setGuestPhone(String guestPhone) {
          this.guestPhone = guestPhone;
     }

     public String getGuestEmail() {
          return guestEmail;
     }

     public void setGuestEmail(String guestEmail) {
          this.guestEmail = guestEmail;
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

     public BookingStatus getStatus() {
          return status;
     }

     public void setStatus(BookingStatus status) {
          this.status = status;
     }

     public String getNotes() {
          return notes;
     }

     public void setNotes(String notes) {
          this.notes = notes;
     }

     public Instant getCreatedAt() {
          return createdAt;
     }

     public void setCreatedAt(Instant createdAt) {
          this.createdAt = createdAt;
     }

     public Instant getUpdatedAt() {
          return updatedAt;
     }

     public void setUpdatedAt(Instant updatedAt) {
          this.updatedAt = updatedAt;
     }

     

}
