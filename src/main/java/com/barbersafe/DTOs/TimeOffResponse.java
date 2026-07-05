package com.barbersafe.DTOs;

import java.time.Instant;
import java.util.UUID;

public class TimeOffResponse {
     private UUID id;
     private Instant startsAt;
     private Instant endsAt;
     private String reason;

     public UUID getId() {
          return id;
     }
     public void setId(UUID id) {
          this.id = id;
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
