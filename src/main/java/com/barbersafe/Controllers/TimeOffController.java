package com.barbersafe.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbersafe.DTOs.CreateTimeOffRequest;
import com.barbersafe.DTOs.TimeOffResponse;
import com.barbersafe.Services.TimeOffService;

@RestController
@RequestMapping("/api")
public class TimeOffController {
     private final TimeOffService timeOffService;

     public TimeOffController(TimeOffService timeOffService) {
          this.timeOffService = timeOffService;
     }

     @PostMapping("/barbers/{barberId}/timeoffs")
     public ResponseEntity<TimeOffResponse> create(@RequestBody CreateTimeOffRequest request, @PathVariable UUID barberId) {
          TimeOffResponse response = this.timeOffService.create(request, barberId);
          return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     @GetMapping("/barbers/{barberId}/timeoffs")
     public ResponseEntity<List<TimeOffResponse>> getByBarberId(@PathVariable UUID barberId) {
          List<TimeOffResponse> responses = this.timeOffService.getByBarberId(barberId);
          return ResponseEntity.ok(responses);
     }

     @DeleteMapping("/timeoffs/{id}")
     public ResponseEntity<Void> delete(@PathVariable UUID id) {
          this.timeOffService.delete(id);
          return ResponseEntity.noContent().build();
     }

}
