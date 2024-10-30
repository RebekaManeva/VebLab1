package veb_labs.service;

import veb_labs.model.Bookings;
import veb_labs.model.Event;

import java.util.List;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Bookings> getBookings();
    void addBooking(String event, int numOfTickets);
}
