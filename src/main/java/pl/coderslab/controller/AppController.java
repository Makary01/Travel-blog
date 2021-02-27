package pl.coderslab.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.Type;
import pl.coderslab.exception.UniqueValuesException;
import pl.coderslab.service.TripService;
import pl.coderslab.service.TypeService;
import pl.coderslab.service.UserService;
import pl.coderslab.utils.CurrentUser;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

@Transactional
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

    @GetMapping("/test")
    @ResponseBody
    public String test(@AuthenticationPrincipal CurrentUser currentUser) throws UniqueValuesException {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            Trip trip = new Trip();
            trip.setUser(currentUser.getUser());
            trip.setTitle("some trip" + i+1);
            trip.setDestination("Lorem ipsum dolor sit amet");
            trip.setCreated(LocalDate.now().minusDays(random.nextInt(10)));
            trip.setContent(" Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc lacinia bibendum lorem, et hendrerit orci sodales quis. Vivamus rhoncus lectus vel dui lobortis aliquet sed ut velit. Nam fermentum, sapien eget faucibus porttitor, ligula nisl porta tellus, sit amet mollis nibh elit id risus. Nullam ut molestie lacus. Praesent tempus eleifend metus, eu ornare sapien imperdiet non. Maecenas eu condimentum orci, hendrerit tincidunt quam. Sed vulputate nibh quam, ut fringilla nunc ornare iaculis. Aliquam finibus pretium porttitor. Donec quis lectus lacus. Curabitur gravida ligula vel leo posuere, sit amet accumsan velit bibendum. Pellentesque dapibus ullamcorper ligula non tempor. Ut non molestie nibh.\n" +
                    "\n" +
                    "Nam enim felis, vestibulum nec leo at, consectetur feugiat erat. Mauris posuere suscipit felis hendrerit vestibulum. Integer non mattis nisl. Curabitur eleifend erat est, in scelerisque dui volutpat ac. Sed eu semper metus. Cras tempus dolor sit amet augue venenatis finibus. Integer dictum fringilla suscipit. ");
            trip.setStartDate(LocalDate.now().minusDays(random.nextInt(10)+20));
            trip.setEndDate(LocalDate.now().minusDays(random.nextInt(10)+10));
            Set<Type> typesForTrip = new HashSet<>();

            for (Type type : types) {
                    if (typesForTrip.size() < 4) {
                        if (random.nextInt(2) == 1) {
                            typesForTrip.add(type);
                        }
                    }
                }
            if(typesForTrip.size()==0){
                Iterator<Type> typeIterator = types.iterator();
                typesForTrip.add(typeIterator.next());
            }
            System.out.println(typesForTrip.toString());
            trip.setTypes(typesForTrip);
//            tripService.saveNewTrip(trip);
        }
        return "success";
    }
}
