package com.barbersafe.DTOs;

public class BarberRegisterRequest {

     private String fullName;
     private String businessName;
     private String email;
     private String password;
     private Integer defaultDurationMinutes;
     private Double price;
     private String phone;

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

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
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

     public String getPhone() {
          return this.phone;
     }

     public void setPhone(String phone){
          this.phone = phone;
     }
}
