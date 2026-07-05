package com.barbersafe.DTOs;

import java.time.Instant;

public class CreateTimeOffRequest {
     private Instant startsAt;
     private Instant endsAt;
     private String reason;

     public Instant getStartAt() {
          return startsAt;
     }
     public void setStartsAt(Instant startsAt) {
          this.startsAt = startsAt;
     }
     public Instant getEndAt() {
          return endsAt;
     }
     public void setEndAt(Instant endsAt) {
          this.endsAt = endsAt;
     }
     public String getReason() {
          return reason;
     }
     public void setReason(String reason) {
          this.reason = reason;
     }
}