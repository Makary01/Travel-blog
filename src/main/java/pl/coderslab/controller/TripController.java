package pl.coderslab.controller;

import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;
import pl.coderslab.service.TripService;
import pl.coderslab.utils.CurrentUser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/app/trip")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    @GetMapping("/{id:\\d+}")
    public String read(@PathVariable Long id, HttpServletResponse response,
                       @AuthenticationPrincipal CurrentUser currentUser, Model model) throws IOException {
        try {
            Trip trip = tripService.findById(id);
            model.addAttribute(trip);
            if (trip.getUser().getId() == currentUser.getUser().getId()) {
                return "app/trip/ownPreview";
            }

        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/trip/preview";
    }


}
