package com.barbersafe.DTOs;

import java.util.UUID;

public class BarberResponse {
     private UUID id;
     private String fullName;
     private String businessName;
     private String email;
     private Integer defaultDurationMinutes;
     private Double price;
     private boolean isActive;

     public boolean isIsActive() {
          return isActive;
     }

     public void setIsActive(boolean isActive) {
          this.isActive = isActive;
     }

     public UUID getId() {
          return this.id;
     }

     public void setId(UUID id) {
          this.id = id;
     }

     public String getFullName() {
          return this.fullName;
     }

     public void setFullName(String fullName) {
          this.fullName = fullName;
     }

     public String getBusinessName() {
          return this.businessName;
     }

     public void setBusinessName(String businessName) {
          this.businessName = businessName;
     }

     public String getEmail() {
          return this.email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public Integer getDefaultDurationMinutes() {
          return this.defaultDurationMinutes;
     }

     public void setDefaultDurationMinutes(Integer defaultDurationMinutes) {
          this.defaultDurationMinutes = defaultDurationMinutes;
     }

     public Double getPrice() {
          return this.price;
     }

     public void setPrice(Double price) {
          this.price = price;
     }

}
