package veb_labs.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import veb_labs.bootstrap.DataHolder;
import veb_labs.model.Bookings;
import veb_labs.model.Event;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class EventRepository {
    List<Bookings> bookings = new ArrayList<>();

    public static List<Event> findAll() {
        return DataHolder.eventList;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.eventList.stream()
                .filter(e -> e.getName().toLowerCase().contains(text.toLowerCase()) ||
                        e.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void addBooking(String event, int numOfTickets) {
        for(Bookings booking : bookings) {
            if(booking.getName().equals(event)){
                booking.setNumberOfTickets(booking.getNumberOfTickets() + numOfTickets);
                return;
            }
        }
        bookings.add(new Bookings(event, numOfTickets));
    }

    public List<Bookings> getBookings() {
        return bookings;
    }
}
