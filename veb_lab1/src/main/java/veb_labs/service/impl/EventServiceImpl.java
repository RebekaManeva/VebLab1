package veb_labs.service.impl;

import org.springframework.stereotype.Service;
import veb_labs.model.Bookings;
import veb_labs.model.Event;
import veb_labs.repository.EventRepository;
import veb_labs.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public List<Bookings> getBookings() {
        return eventRepository.getBookings();
    }

    @Override
    public void addBooking(String event, int numOfTickets) {
        eventRepository.addBooking(event, numOfTickets);
    }
}
