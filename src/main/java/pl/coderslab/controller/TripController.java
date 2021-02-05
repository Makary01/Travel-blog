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
    public String createTripForm(Model model) {
        model.addAttribute("trip", new Trip());
        model.addAttribute("types", types);
        return "app/trip/create";
    }

    @PostMapping("/add")
    public String createTripAction(@ModelAttribute @Valid Trip trip, BindingResult result,
                                   @AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("types", types);
        if (result.hasErrors()) return "app/trip/create";

        if ((trip.getEndDate()).isBefore(trip.getStartDate())) {
            result.addError(new ObjectError("endDate", "You cant end journey before starting it"));
            return "app/trip/create";
        }
        if (LocalDate.now().isBefore(trip.getEndDate())) {
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

    @GetMapping("/edit/{id:\\d+}")
    public String editForm(@PathVariable Long id, HttpServletResponse response,
                           @AuthenticationPrincipal CurrentUser currentUser, Model model) throws IOException {
        try {
            Trip trip = tripService.findById(id);
            model.addAttribute(trip);
            model.addAttribute("types", types);
            if (trip.getUser().getId() == currentUser.getUser().getId()) {
                return "app/trip/edit";
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/trip/edit";
    }

    @PostMapping("/edit/{id:\\d+}")
    public String editAction(@PathVariable Long id, HttpServletResponse response,
                             @ModelAttribute("trip") @Valid Trip tripEdited, BindingResult result,
                             @AuthenticationPrincipal CurrentUser currentUser, Model model) throws IOException {
        model.addAttribute("types", types);
        try {
            Trip tripToEdit = tripService.findById(id);
            if(!(tripToEdit.getUser().getId() == currentUser.getUser().getId())){
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }else {
                if (result.hasErrors()) return "app/trip/edit";
                tripToEdit.setTitle(tripEdited.getTitle());
                tripToEdit.setTypes(tripEdited.getTypes());
                tripToEdit.setDestination(tripEdited.getDestination());
                tripToEdit.setContent(tripEdited.getContent());
                tripToEdit.setStartDate(tripEdited.getStartDate());
                tripToEdit.setEndDate(tripEdited.getEndDate());
                try {
                    tripService.saveEditedTrip(tripToEdit);
                    return "redirect:/app/dashboard";
                } catch (UniqueValuesException e) {
                    result.addError(new ObjectError(e.getObjectName(), e.getMessage()));
                    return "app/trip/create";
                }
            }
        } catch (NotFoundException | IOException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/trip/edit";
    }


    @GetMapping("/delete/{id:\\d+}")
    public String deleteForm(@PathVariable Long id, HttpServletResponse response,
                             @AuthenticationPrincipal CurrentUser currentUser, Model model) throws IOException {
        try {
            Trip trip = tripService.findById(id);
            if (trip.getUser().getId() == currentUser.getUser().getId()) {
                model.addAttribute("id",id);
                model.addAttribute("title",trip.getTitle());
                return "app/trip/confirmDelete";
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/trip/confirmDelete";
    }

    @PostMapping("/delete/{id:\\d+}")
    public String deleteAction(@PathVariable Long id, HttpServletResponse response,
                             @AuthenticationPrincipal CurrentUser currentUser, Model model) throws IOException {
        try {
            Trip trip = tripService.findById(id);
            if (trip.getUser().getId() == currentUser.getUser().getId()) {
                tripService.delete(trip);
                return "redirect:/app/dashboard";
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (NotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/trip/confirmDelete";
    }

}
