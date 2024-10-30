package veb_labs.bootstrap;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import veb_labs.model.Event;
import veb_labs.model.EventBooking;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> eventList = new ArrayList<>();
    public static List<EventBooking> eventBookings = new ArrayList<>();

    @PostConstruct
    public void init() {
        eventList.add(new Event("Event1test", "Desc1", 4.6));
        eventList.add(new Event("Event2", "Desc2", 4.2));
        eventList.add(new Event("Event3", "Desc3", 3.9));
        eventList.add(new Event("Event4", "Desc4", 3.5));
        eventList.add(new Event("Event5", "Desc5", 2.3));
        eventList.add(new Event("Event6", "Desc6test", 2.0));


        eventBookings.add(new EventBooking("Event1", "Attendee", "Address", 3L));
    }
}
