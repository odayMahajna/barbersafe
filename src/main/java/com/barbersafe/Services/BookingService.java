package com.barbersafe.Services;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.barbersafe.DTOs.AvailableSlotResponse;
import com.barbersafe.DTOs.BookingResponse;
import com.barbersafe.DTOs.CreateBookingRequest;
import com.barbersafe.Repositories.AvailabilityRepo;
import com.barbersafe.Repositories.BarberRepo;
import com.barbersafe.Repositories.BookingRepo;
import com.barbersafe.Repositories.TimeOffRepo;
import com.barbersafe.models.Availability;
import com.barbersafe.models.Barber;
import com.barbersafe.models.Booking;
import com.barbersafe.models.TimeOff;

@Service
public class BookingService {
     private final BookingRepo bookingRepo;
     private final BarberRepo barberRepo;
     private final AvailabilityRepo availabilityRepo;
     private final TimeOffRepo timeOffRepo;

     public BookingService(BookingRepo bookingRepo, BarberRepo barberRepo, AvailabilityRepo availabilityRepo,
               TimeOffRepo timeOffRepo) {
          this.bookingRepo = bookingRepo;
          this.barberRepo = barberRepo;
          this.availabilityRepo = availabilityRepo;
          this.timeOffRepo = timeOffRepo;
     }

     public BookingResponse create(CreateBookingRequest request) {
          Barber barber = barberRepo.findById(request.getBarberId()).orElseThrow(
                    () -> new IllegalArgumentException("Barber not found!"));

          Instant endsAt = request.getStartsAt().plus(Duration.ofMinutes(barber.getDefaultDurationMinutes()));

          Booking booking = new Booking();
          booking.setBarber(barber);
          booking.setStartsAt(request.getStartsAt());
          booking.setEndsAt(endsAt);
          booking.setGuestName(request.getGuestName());
          booking.setGuestPhone(request.getGuestPhone());
          booking.setGuestEmail(request.getGuestEmail());
          booking.setStatus(Booking.BookingStatus.CONFIRMED);

          try {
               Booking savedBooking = bookingRepo.save(booking);
               return toResponse(savedBooking);
          } catch (DataIntegrityViolationException e) {
               throw new IllegalStateException("this time slot is not longer available!");
          }
     }

     public List<BookingResponse> getByBarberId(UUID barberId, YearMonth month) {

          Instant startOfMonth = month.atDay(1).atStartOfDay(ZoneOffset.UTC).toInstant();
          Instant endOfMonth = month.atEndOfMonth().atTime(23, 59, 59).atZone(ZoneOffset.UTC).toInstant();

          List<Booking> bookings = bookingRepo.findByBarberIdAndStartsAtBetween(barberId, startOfMonth, endOfMonth);

          List<BookingResponse> responses = new ArrayList<>();
          for (Booking booking : bookings) {
               responses.add(toResponse(booking));
          }

          return responses;
     }

     public void delete(UUID id) {
          Booking booking = bookingRepo.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("Booking Not Found!"));

          bookingRepo.delete(booking);
     }

     public List<AvailableSlotResponse> getAvailableSlots(UUID barberId, LocalDate date) {

          Barber barber = barberRepo.findById(barberId)
                    .orElseThrow(() -> new IllegalArgumentException("Barber not found"));

          ZoneId zone = ZoneId.of(barber.getTimezone());
          int durationMinutes = barber.getDefaultDurationMinutes();

          short dayOfWeek = (short) (date.getDayOfWeek().getValue() % 7);
          List<Availability> availabilities = availabilityRepo.findByBarberIdAndDayOfWeek(barberId, dayOfWeek);

          if (availabilities.isEmpty()) {
               return new ArrayList<>();
          }

          Instant dayStart = date.atStartOfDay(zone).toInstant();
          Instant dayEnd = date.plusDays(1).atStartOfDay(zone).toInstant();

          List<TimeOff> timeOffs = timeOffRepo.findOverlapping(barberId, dayStart, dayEnd);
          List<Booking> bookings = bookingRepo.findOverlapping(barberId, dayStart, dayEnd);

          List<Instant[]> busyIntervals = new ArrayList<>();
          for (TimeOff timeOff : timeOffs) {
               busyIntervals.add(new Instant[] { timeOff.getStartsAt(), timeOff.getEndsAt() });
          }
          for (Booking booking : bookings) {
               busyIntervals.add(new Instant[] { booking.getStartsAt(), booking.getEndsAt() });
          }

          busyIntervals.sort((a, b) -> a[0].compareTo(b[0]));

          List<AvailableSlotResponse> slots = new ArrayList<>();

          for (Availability availability : availabilities) {
               Instant windowStart = date.atTime(availability.getStartTime()).atZone(zone).toInstant();
               Instant windowEnd = date.atTime(availability.getEndTime()).atZone(zone).toInstant();

               Instant cursor = windowStart;

               for (Instant[] busy : busyIntervals) {
                    Instant busyStart = busy[0];
                    Instant busyEnd = busy[1];

                    if (busyEnd.isBefore(windowStart) || busyStart.isAfter(windowEnd)) {
                         continue;
                    }

                    if (busyStart.isAfter(cursor)) {
                         slots.addAll(sliceIntoSlots(cursor, busyStart, durationMinutes));
                    }

                    if (busyEnd.isAfter(cursor)) {
                         cursor = busyEnd;
                    }
               }

               if (cursor.isBefore(windowEnd)) {
                    slots.addAll(sliceIntoSlots(cursor, windowEnd, durationMinutes));
               }
          }

          return slots;
     }

     private List<AvailableSlotResponse> sliceIntoSlots(Instant from, Instant to, int durationMinutes) {
          List<AvailableSlotResponse> result = new ArrayList<>();
          Instant slotStart = from;

          while (true) {
               Instant slotEnd = slotStart.plus(Duration.ofMinutes(durationMinutes));
               if (slotEnd.isAfter(to)) {
                    break;
               }
               AvailableSlotResponse slot = new AvailableSlotResponse();
               slot.setStartsAt(slotStart);
               slot.setEndsAt(slotEnd);
               result.add(slot);

               slotStart = slotEnd;
          }

          return result;
     }

     private BookingResponse toResponse(Booking booking) {
          BookingResponse response = new BookingResponse();
          response.setBarberId(booking.getBarber().getId());
          response.setGuestName(booking.getGuestName());
          response.setGuestEmail(booking.getGuestEmail());
          response.setGuestPhone(booking.getGuestPhone());
          response.setId(booking.getId());
          response.setStartsAt(booking.getStartsAt());
          response.setEndsAt(booking.getEndsAt());
          response.setStatus(booking.getStatus().name());

          return response;
     }

}
