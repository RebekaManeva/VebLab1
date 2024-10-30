package veb_labs.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import veb_labs.model.Event;
import veb_labs.service.impl.EventServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name="EventListServlet", urlPatterns = {""})
public class  EventListServlet extends HttpServlet {
    private final EventServiceImpl eventService;
    private final SpringTemplateEngine templateEngine;

    public EventListServlet(EventServiceImpl eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        List<Event> eventList = eventService.listAll();
        String search = req.getParameter("search");
        String rating = req.getParameter("minRating");


        if(search != null) {
            String textStr = search.toString();
            if(!textStr.isEmpty()) {
                eventList = eventService.searchEvents(textStr);
            }
        }
        if(rating != null){
            String ratingStr = rating.toString();
            if(!ratingStr.isEmpty()){
                eventList = eventList.stream()
                        .filter(e -> e.getPopularityScore() >= Double.parseDouble(rating))
                        .toList();
            }
        }

        context.setVariable("events", eventList);
        templateEngine.process("listEvents.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        String eventName = req.getParameter("selectedEvent");
        String numOfTickets = req.getParameter("numTickets");

        eventService.addBooking(eventName, Integer.parseInt(numOfTickets));
        context.setVariable("hostName", req.getRemoteHost());
        context.setVariable("hostAddress", req.getRemoteAddr());
        context.setVariable("eventName", eventName);
        context.setVariable("numOfTickets", numOfTickets);
        context.setVariable("Bookings", eventService.getBookings());
        templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }
}
