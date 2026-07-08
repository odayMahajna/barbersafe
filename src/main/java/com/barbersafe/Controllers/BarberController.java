package com.barbersafe.Controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbersafe.DTOs.BarberLoginRequest;
import com.barbersafe.DTOs.BarberRegisterRequest;
import com.barbersafe.DTOs.BarberResponse;
import com.barbersafe.Services.BarberService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {
     private final BarberService barberService;

     public BarberController(BarberService barberService) {
          this.barberService = barberService;
     }

     @PostMapping("/register")
     public ResponseEntity<BarberResponse> register(@RequestBody BarberRegisterRequest request) {
          BarberResponse response = this.barberService.registerBarber(request);
          return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     @PostMapping("/login")
     public ResponseEntity<BarberResponse> login(@RequestBody BarberLoginRequest request) {
          BarberResponse response = this.barberService.login(request);
          return ResponseEntity.ok(response);
     }

     @PostMapping("/{id}/toggle-active")
     public ResponseEntity<BarberResponse> toggleActive(@PathVariable UUID id) {
          BarberResponse response = this.barberService.toggleActive(id);
          return ResponseEntity.ok(response);
     }

}
