package com.barbersafe.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.barbersafe.DTOs.AvailabilityResponse;
import com.barbersafe.DTOs.CreateAvailabilityRequest;
import com.barbersafe.Repositories.AvailabilityRepo;
import com.barbersafe.Repositories.BarberRepo;
import com.barbersafe.models.Availability;
import com.barbersafe.models.Barber;

@Service
public class AvailabilityService {
     private final AvailabilityRepo availabilityRepository;
     private final BarberRepo barberRepo;

     public AvailabilityService(AvailabilityRepo availabilityRepository,BarberRepo barberRepo) {
          this.availabilityRepository = availabilityRepository;
          this.barberRepo = barberRepo;
     }

     public AvailabilityResponse create(CreateAvailabilityRequest request, UUID barberId) {
          Availability availability= new Availability();

          Barber barber = barberRepo.findById(barberId).orElseThrow(
               () -> new IllegalArgumentException("Barber not found")
          );

          availability.setBarber(barber);
          availability.setDayOfWeek(request.getDayOfWeek());
          availability.setStartTime(request.getStartTime());
          availability.setEndTime(request.getEndTime());
          Availability savedAvailability=availabilityRepository.save(availability);
          return toResponse(savedAvailability);
     }

     public List<AvailabilityResponse> getByBarberId(UUID barberId){
          List<AvailabilityResponse> responses= new ArrayList<>();
          List<Availability> availabilities= availabilityRepository.findByBarberId(barberId);
          
          //convert from normal List<availability> to List<availabilityResponse>
          for(Availability availability : availabilities){
               responses.add(toResponse(availability));
          }

          return responses;
     }

     public void delete(UUID id){
          Availability availability = availabilityRepository.findById(id).orElseThrow(
               () -> new IllegalArgumentException("Availability not found!")
          );
          availabilityRepository.delete(availability);
     }


     private AvailabilityResponse toResponse(Availability availability) {
          AvailabilityResponse response = new AvailabilityResponse();
          response.setId(availability.getId());
          response.setDayOfWeek(availability.getDayOfWeek());
          response.setStartTime(availability.getStartTime());
          response.setEndTime(availability.getEndTime());
          return response;
     }
}
