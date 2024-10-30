package veb_labs.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import veb_labs.model.Bookings;
import veb_labs.service.EventService;

import java.io.IOException;
import java.util.List;

@WebServlet(name="EventBookingServlet", urlPatterns = {"/events/event-booking"})
public class EventBookingServlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine templateEngine;

    public EventBookingServlet(EventService eventService, SpringTemplateEngine templateEngine) {
        this.eventService = eventService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        String search = req.getParameter("bookingSearch");
        List<Bookings> bookings = eventService.getBookings().stream()
                .filter(b -> b.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();

        context.setVariable("savedBookings", bookings);
        templateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }


}
