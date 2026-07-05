package com.barbersafe.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.barbersafe.DTOs.CreateTimeOffRequest;
import com.barbersafe.DTOs.TimeOffResponse;
import com.barbersafe.Repositories.BarberRepo;
import com.barbersafe.Repositories.BookingRepo;
import com.barbersafe.Repositories.TimeOffRepo;
import com.barbersafe.models.Barber;
import com.barbersafe.models.Booking;
import com.barbersafe.models.TimeOff;

@Service
public class TimeOffService {

     private final BookingRepo bookingRepo;
     private final TimeOffRepo timeOffRepo;
     private final BarberRepo barberRepo;

     public TimeOffService(BookingRepo bookingRepo, TimeOffRepo timeOffRepo, BarberRepo barberRepo) {
          this.timeOffRepo = timeOffRepo;
          this.bookingRepo = bookingRepo;
          this.barberRepo = barberRepo;
     }

     public TimeOffResponse create(CreateTimeOffRequest request, UUID barberId) {

          Barber barber = barberRepo.findById(barberId).orElseThrow(
                    () -> new IllegalArgumentException("Barber Not found"));

          // confirm no overlap
          List<Booking> overLapping = bookingRepo.findOverlapping(barberId, request.getStartAt(), request.getEndAt());

          if (!overLapping.isEmpty()) {
               throw new IllegalArgumentException("You have an appointment at this time! ");
          }

          TimeOff timeOff = new TimeOff();
          timeOff.setBarber(barber);
          timeOff.setStartsAt(request.getStartAt());
          timeOff.setEndsAt(request.getEndAt());
          timeOff.setReason(request.getReason());
          TimeOff savedTimeOff = timeOffRepo.save(timeOff);

          return toResponse(savedTimeOff);
     }

     public List<TimeOffResponse> getByBarberId(UUID barberId) {
          List<TimeOff> timeOffs = timeOffRepo.findByBarberId(barberId);
          List<TimeOffResponse> responses = new ArrayList<>();

          for (TimeOff time : timeOffs) {
               responses.add(toResponse(time));
          }

          return responses;
     }

     public void delete(UUID id) {
          TimeOff timeOff = timeOffRepo.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("TimeOff not found"));
          timeOffRepo.delete(timeOff);
     }

     private TimeOffResponse toResponse(TimeOff timeOff) {
          TimeOffResponse response = new TimeOffResponse();
          response.setId(timeOff.getId());
          response.setStartsAt(timeOff.getStartsAt());
          response.setEndsAt(timeOff.getEndsAt());
          response.setReason(timeOff.getReason());

          return response;
     }

}
