package io.lostmore.Secaunt.controller;

import io.lostmore.Secaunt.model.Booking;
import io.lostmore.Secaunt.model.Resource;
import io.lostmore.Secaunt.repository.BookingRepository;
import io.lostmore.Secaunt.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
        if (booking.getResource() == null || booking.getResource().getId() == null) {
            return ResponseEntity.badRequest().body("resource is not specified");
        }

        Resource resource = resourceRepository.findById(booking.getResource().getId()).orElse(null);
        if (resource == null) {
            return ResponseEntity.badRequest().body("Nothing.");
        }

        List<Booking> overlapping = bookingRepository.findByResourceIdAndEndTimeAfterAndStartTimeBefore(
                resource.getId(), booking.getStartTime(), booking.getEndTime()
        );

        if (!overlapping.isEmpty()) {
            return ResponseEntity.badRequest().body("Time is busy.");
        }

        booking.setResource(resource);
        Booking saved = bookingRepository.save(booking);
        return ResponseEntity.ok(saved);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
