package veb_labs.service.impl;


import org.springframework.stereotype.Service;
import veb_labs.model.EventBooking;
import veb_labs.service.EventBookingService;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
    }
}