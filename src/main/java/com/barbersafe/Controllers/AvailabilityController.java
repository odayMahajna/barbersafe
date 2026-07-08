package com.barbersafe.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.barbersafe.DTOs.AvailabilityResponse;
import com.barbersafe.DTOs.CreateAvailabilityRequest;
import com.barbersafe.Services.AvailabilityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AvailabilityController {
     private final AvailabilityService availabilityService;

     public AvailabilityController(AvailabilityService availabilityService) {
          this.availabilityService = availabilityService;
     }

     @GetMapping("/api/barbers/{barberId}/availability")
     public ResponseEntity<List<AvailabilityResponse>> getByBarberId(@PathVariable UUID barberId) {
          List<AvailabilityResponse> responses = this.availabilityService.getByBarberId(barberId);
          return ResponseEntity.ok(responses);
     }

     @PostMapping("/api/barbers/{barberId}/availability")
     public ResponseEntity<AvailabilityResponse> create(@PathVariable UUID barberId,@RequestBody CreateAvailabilityRequest request) {
          AvailabilityResponse response= this.availabilityService.create(request, barberId);
          return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     @DeleteMapping("/api/availability/{id}")
     public ResponseEntity<Void> delete(@PathVariable UUID id)
     {
          this.availabilityService.delete(id);
          return ResponseEntity.noContent().build();
     }
}
