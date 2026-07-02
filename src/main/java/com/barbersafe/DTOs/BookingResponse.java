package com.barbersafe.DTOs;

import java.time.Instant;
import java.util.UUID;

public class BookingResponse {
     private UUID id;
     private UUID barberId;
     private Instant startsAt;
     private Instant endsAt;
     private String status;
     private String guestName;
     private String guestPhone;
     private String guestEmail;


     public UUID getId() {
          return id;
     }
     public void setId(UUID id) {
          this.id = id;
     }
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
     public Instant getEndsAt() {
          return endsAt;
     }
     public void setEndsAt(Instant endsAt) {
          this.endsAt = endsAt;
     }
     public String getStatus() {
          return status;
     }
     public void setStatus(String status) {
          this.status = status;
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
