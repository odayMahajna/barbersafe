package com.barbersafe.DTOs;

import java.time.Instant;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateBookingRequest {
     @NotNull
     private UUID barberId;

     @NotNull
     private Instant startsAt;

     @NotBlank
     private String guestName;

     @NotBlank
     private String guestPhone;

     @NotBlank
     private String guestEmail;

     public UUID getBarberId() {
          return barberId;
     }

     public void setBarberId(UUID barberId) {
          this.barberId = barberId;
     }

     public Instant getStartsAt() {
          return startsAt;
     }

     public void setStartsAt(Instant startsAt) {
          this.startsAt = startsAt;
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


     
}
