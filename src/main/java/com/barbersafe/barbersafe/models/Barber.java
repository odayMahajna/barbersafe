package com.barbersafe.barbersafe.models;

import java.util.List;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "barbers")
public class Barber {
     @Id
     @GeneratedValue(strategy = GenerationType.UUID)
     @Column(name = "id", nullable = false, updatable = false)
     private UUID id;

     @Column(name = "fullname", nullable = false, length = 150)
     private String fullname;

     @Column(name = "email", nullable = false, unique = true, length = 255)
     private String email;

     @Column(name = "phone", nullable = false, length = 10)
     private String phone;

     @Column(name = "password_hash", nullable = false, length = 255)
     private String password_hash;

     @Column(name = "business_name", length = 100)
     private String buisnessName;

     @Column(name = "bio")
     private String bio;

     @Column(name = "address", length = 255)
     private String address;

     @Column(name = "timezone", nullable = false, length = 50)
     private String timezone = "UTC";

     @Column(name = "default_duration_minutes", nullable = false)
     private Integer defaultDurationMinutes = 60;

     @Column(name = "price")
     private Integer price;

     @Column(name = "is_active", nullable = false)
     private Boolean isActive = true;

     @Column(name = "created_at", nullable = false, updatable = false)
     private Instant createdAt;

     @Column(name = "updated_at", nullable = false)
     private Instant updatedAt;

     @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Availability> availability = new ArrayList<>();

     @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<TimeOff> timeOffPeriods = new ArrayList<>();

     @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Booking> bookings = new ArrayList<>();

     public Barber() {
     }

     public UUID getId() {
          return id;
     }

     public void setId(UUID id) {
          this.id = id;
     }

     public String getFullname() {
          return fullname;
     }

     public void setFullname(String fullname) {
          this.fullname = fullname;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPhone() {
          return phone;
     }

     public void setPhone(String phone) {
          this.phone = phone;
     }

     public String getPassword_hash() {
          return password_hash;
     }

     public void setPassword_hash(String password_hash) {
          this.password_hash = password_hash;
     }

     public String getBuisnessName() {
          return buisnessName;
     }

     public void setBuisnessName(String buisnessName) {
          this.buisnessName = buisnessName;
     }

     public String getBio() {
          return bio;
     }

     public void setBio(String bio) {
          this.bio = bio;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public String getTimezone() {
          return timezone;
     }

     public void setTimezone(String timezone) {
          this.timezone = timezone;
     }

     public Integer getDefaultDurationMinutes() {
          return defaultDurationMinutes;
     }

     public void setDefaultDurationMinutes(Integer defaultDurationMinutes) {
          this.defaultDurationMinutes = defaultDurationMinutes;
     }

     public Integer getPrice() {
          return price;
     }

     public void setPrice(Integer price) {
          this.price = price;
     }

     public Boolean getIsActive() {
          return isActive;
     }

     public void setIsActive(Boolean isActive) {
          this.isActive = isActive;
     }

     public Instant getCreatedAt() {
          return createdAt;
     }

     public Instant getUpdatedAt() {
          return updatedAt;
     }

     public List<Availability> getAvailabilityRules() {
          return this.availability;
     }

     public void setAvailabilityRules(List<Availability> availability) {
          this.availability = availability;
     }

     public List<TimeOff> getTimeOffPeriods() {
          return timeOffPeriods;
     }

     public void setTimeOffPeriods(List<TimeOff> timeOffPeriods) {
          this.timeOffPeriods = timeOffPeriods;
     }

     public List<Booking> getBookings() {
          return bookings;
     }

     public void setBookings(List<Booking> bookings) {
          this.bookings = bookings;
     }
}