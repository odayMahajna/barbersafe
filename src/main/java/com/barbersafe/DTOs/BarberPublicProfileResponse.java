package com.barbersafe.DTOs;

import java.util.UUID;

public class BarberPublicProfileResponse {
     
     private UUID id;
     private String fullName;
     private String businessName;
     private Integer defaultDurationMinutes;
     private Double price;

     public UUID getId() {
          return id;
     }

     public void setId(UUID id) {
          this.id = id;
     }

     public String getFullName() {
          return fullName;
     }

     public void setFullName(String fullName) {
          this.fullName = fullName;
     }

     public String getBusinessName() {
          return businessName;
     }

     public void setBusinessName(String businessName) {
          this.businessName = businessName;
     }

     public Integer getDefaultDurationMinutes() {
          return defaultDurationMinutes;
     }

     public void setDefaultDurationMinutes(Integer defaultDurationMinutes) {
          this.defaultDurationMinutes = defaultDurationMinutes;
     }

     public Double getPrice() {
          return price;
     }

     public void setPrice(Double price) {
          this.price = price;
     }
}
