package veb_labs.service;

import veb_labs.model.EventBooking;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
}
