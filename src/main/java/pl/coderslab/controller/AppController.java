package pl.coderslab.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.Type;
import pl.coderslab.service.TripService;
import pl.coderslab.service.TypeService;
import pl.coderslab.service.UserService;
import pl.coderslab.utils.CurrentUser;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/app")
public class AppController {


    TypeService typeService;
    TripService tripService;
    UserService userService;
    private Set<Type> types;

    public AppController(TypeService typeService,
                         TripService tripService,
                         UserService userService) {
        this.typeService = typeService;
        this.tripService = tripService;
        this.userService = userService;
        types = typeService.findAllSet();
    }

    //============================================
    //          APP DASHBOARD
    //============================================
    @GetMapping("/dashboard")
    public String readAll(Model model, @AuthenticationPrincipal CurrentUser currentUser,
                          @RequestParam(name = "page", defaultValue = "0") String page,
                          @RequestParam(name = "orderBy", defaultValue = "created") String orderBy,
                          @RequestParam(name = "order", defaultValue = "desc") String order,
                          @RequestParam(name = "types", defaultValue = "") String requestedTypes) {
        //assigning page number
        Integer pageNumber;
        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageNumber = 0;
        }

        //assigning orderBy property
        if (!(orderBy.equals("created") || orderBy.equals("title") || orderBy.equals("startDate") || orderBy.equals("endDate"))) {
            orderBy = "created";
        }

        //assigning requested types
        String[] typesStringArray = requestedTypes.split(",");
        Set<Long> typeIdsSet = new HashSet<>();
        for (String typeAsString : typesStringArray) {
            try {
                typeIdsSet.add(Long.parseLong(typeAsString));
            } catch (NumberFormatException e) {
            }
        }
        Set<Type> requestedTypesSet = typeService.findByIdIn(typeIdsSet);
        if (requestedTypesSet.size() < 1) {
            requestedTypesSet = types;
        }

        //getting trip page based on order
        Page<Trip> tripPage;
        if (order.equals("asc")) {
            tripPage = tripService.findPageByTypesOrderByX(requestedTypesSet,
                    PageRequest.of(pageNumber, 20, Sort.by(orderBy).ascending()));
        } else {
            tripPage = tripService.findPageByTypesOrderByX(requestedTypesSet,
                    PageRequest.of(pageNumber, 20, Sort.by(orderBy).descending()));
        }

        //if page number is invalid
        if (pageNumber > tripPage.getTotalPages()) {
            pageNumber = 0;
            if (order.equals("asc")) {
                tripPage = tripService.findPageByTypesOrderByX( requestedTypesSet,
                        PageRequest.of(pageNumber, 20, Sort.by(orderBy).ascending()));
            } else {
                tripPage = tripService.findPageByTypesOrderByX( requestedTypesSet,
                        PageRequest.of(pageNumber, 20, Sort.by(orderBy).descending()));
            }
        }

        model.addAttribute("user", currentUser.getUser());
        model.addAttribute("allTypes", types);
        model.addAttribute("trips", tripPage.getContent());
        model.addAttribute("currentPage", pageNumber + 1);
        model.addAttribute("totalPages", tripPage.getTotalPages());
        return "app/dashboard";
    }

}
