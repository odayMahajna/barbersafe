package com.barbersafe.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.barbersafe.DTOs.BarberLoginRequest;
import com.barbersafe.DTOs.BarberRegisterRequest;
import com.barbersafe.DTOs.BarberResponse;
import com.barbersafe.Repositories.BarberRepo;
import com.barbersafe.models.Barber;

public class BarberService {
     
     private final BarberRepo barberRepository;
     private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

     public BarberService(BarberRepo barberRepository) {
         this.barberRepository = barberRepository;
     }

     public BarberResponse registerBarber(BarberRegisterRequest barberRequest) {
          
          if (barberRepository.findByEmail(barberRequest.getEmail()).isPresent()) {
               throw new IllegalArgumentException("Email already in use");
          }

          Barber barber = new Barber();
          barber.setFullname(barberRequest.getFullName());
          barber.setEmail(barber.getEmail());
          barber.setPassword_hash(passwordEncoder.encode(barberRequest.getPassword()));
          barber.setPhone(barberRequest.getPhone());
          barber.setBuisnessName(barberRequest.getBusinessName());
          barber.setDefaultDurationMinutes(barberRequest.getDefaultDurationMinutes());
          barber.setPrice(barberRequest.getPrice());

          Barber savedBarber =barberRepository.save(barber);

          return toResponse(savedBarber);
     }

     public BarberResponse login(BarberLoginRequest loginRequest){

          //check Email
          Barber barber = barberRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
               () -> new IllegalArgumentException("Invalid email")
          );

          // check password
          if(!passwordEncoder.matches(loginRequest.getPassword(), barber.getPassword_hash())){
               throw new IllegalArgumentException("Invalid password");
          }

          return toResponse(barber);
     }


     private BarberResponse toResponse(Barber barber) {
          BarberResponse response = new BarberResponse();
          response.setId(barber.getId());
          response.setFullName(barber.getFullname());
          response.setEmail(barber.getEmail());
          response.setBusinessName(barber.getBuisnessName());
          response.setDefaultDurationMinutes(barber.getDefaultDurationMinutes());
          response.setPrice(barber.getPrice());
          response.setIsActive(barber.getIsActive());
          return response;
     }   
}
