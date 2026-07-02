package com.barbersafe.DTOs;

import java.time.Instant;

public class AvailableSlotResponse {
     private Instant startsAt;
     private Instant endsAt;
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

     
}
