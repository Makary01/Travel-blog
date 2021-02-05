package pl.coderslab.controller;

import javassist.NotFoundException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.Type;
import pl.coderslab.entity.User;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.service.TripService;
import pl.coderslab.service.TypeService;
import pl.coderslab.utils.CurrentUser;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app/trip")
public class TripController {

    private final TripService tripService;
    private final TypeService typeService;
    private List<Type> types;

    public TripController(TripService tripService, TypeService typeService) {
        this.tripService = tripService;
        this.typeService = typeService;
        types = typeService.findAllTypes();
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
//        sdf.setLenient(true);
//        binder.registerCustomEditor(LocalDate.class, new CustomDateEditor(sdf, true));
//    }



    @GetMapping("/add")
    public String createTrip(Model model){
        model.addAttribute("trip", new Trip());
        model.addAttribute("types",types);
        return "app/trip/create";
    }
    @PostMapping("/add")
    public String createTripAction(@ModelAttribute @Valid Trip trip, BindingResult result,
                                   @AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("types",types);
        if (result.hasErrors()) return "app/trip/create";

        if((trip.getEndDate()).isBefore(trip.getStartDate())){
            result.addError(new ObjectError("endDate", "You cant end journey before starting it"));
            return "app/trip/create";
        }
        if(LocalDate.now().isBefore(trip.getEndDate())){
            result.addError(new ObjectError("endDate", "Enter valid end date"));
            return "app/trip/create";
        }

        try {
            trip.setUser(currentUser.getUser());
            tripService.saveNewTrip(trip);
            return "redirect:/app/dashboard";
        } catch (UniqueValuesException e) {
            result.addError(new ObjectError(e.getObjectName(), e.getMessage()));
            return "app/trip/create";
        }

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
