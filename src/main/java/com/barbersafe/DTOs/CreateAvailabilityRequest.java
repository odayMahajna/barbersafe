package com.barbersafe.DTOs;

import java.time.LocalTime;

public class CreateAvailabilityRequest {
     
     private Short dayOfWeek;
     private LocalTime startTime;
     private LocalTime endTime;

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
