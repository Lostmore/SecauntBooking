package io.lostmore.Secaunt.repository;

import io.lostmore.Secaunt.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByResourceIdAndEndTimeAfterAndStartTimeBefore(
            Long resourceId, LocalDateTime start, LocalDateTime end
    );
}