package veb_labs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventBooking {
    String eventName;
    String attendeeName;
    String attendeeAddress;
    Long numberOfTickets;
}
