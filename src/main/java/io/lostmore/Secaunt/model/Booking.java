package io.lostmore.Secaunt.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Resource resource;

    private String bookedBy;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Booking() {}

    public Booking(Resource resource, String bookedBy, LocalDateTime startTime, LocalDateTime endTime) {
        this.resource = resource;
        this.bookedBy = bookedBy;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }
}

