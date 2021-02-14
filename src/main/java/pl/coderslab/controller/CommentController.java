package pl.coderslab.controller;

import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Trip;
import pl.coderslab.entity.User;
import pl.coderslab.repositories.CommentRepository;
import pl.coderslab.service.CommentService;
import pl.coderslab.service.TripService;
import pl.coderslab.utils.CurrentUser;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/app/comment")
public class CommentController {

    private TripService tripService;
    private CommentService commentService;
    CommentRepository commentRepository;

    public CommentController(TripService tripService, CommentService commentService, CommentRepository commentRepository) {
        this.tripService = tripService;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }


    @PostMapping("/add/{id:\\d+}")
    public String add(@AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("id") Long id,
                             @ModelAttribute Comment comment, BindingResult result){
        if(result.hasErrors()) return "redirect:/app/trip/" + id;

        User user = currentUser.getUser();
        user.setTrips(new ArrayList<Trip>());
        comment.setUser(user);
        comment.setCreated(LocalDate.now());
        Trip trip;
        try {
            trip = tripService.findById(id);
        } catch (NotFoundException e) {
            return "redirect:/app/trip/" + id;
        }
        comment.setTrip(trip);
        commentService.saveComment(comment);
        return "redirect:/app/trip/" + id;
    }

    @GetMapping("/edit/{id:\\d+}")
    public String edit(@AuthenticationPrincipal CurrentUser currentUser,
                             @PathVariable("id") Long id, HttpServletResponse response,Model model) throws IOException {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()){
            if(commentOptional.get().getUser().getId() == currentUser.getUser().getId()){
                model.addAttribute("comment",commentOptional.get());
            }else {
                model.addAttribute("comment",new Comment());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }else {
            model.addAttribute("comment",new Comment());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/comment/edit";
    }

    @PostMapping("/edit/{id:\\d+}")
    public String editAction(@AuthenticationPrincipal CurrentUser currentUser,
                                    @PathVariable("id") Long id, HttpServletResponse response,Model model,
                                    @ModelAttribute("comment") @Valid Comment comment, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("comment",comment);
            return "redirect:/app/comment/edit/"+id;
        }

        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()){
            Comment commentToSave = commentOptional.get();
            if(commentToSave.getUser().getId() == currentUser.getUser().getId()){
                commentToSave.setContent(comment.getContent());
                commentService.saveComment(commentToSave);
                return "redirect:/app/trip/"+commentToSave.getTrip().getId();
            }else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return "redirect:/app/trip/"+commentToSave.getTrip().getId();
            }
        }else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return "redirect:/app/trip/";
        }

    }

    @GetMapping("/delete/{id:\\d+}")
    public String deleteCommentForm(@AuthenticationPrincipal CurrentUser currentUser,
                                    @PathVariable("id") Long id, HttpServletResponse response,Model model) throws IOException {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()){
            if(commentOptional.get().getUser().getId() == currentUser.getUser().getId()){
                model.addAttribute("id",commentOptional.get().getId());
            }else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/comment/delete";
    }

    @PostMapping("/delete/{id:\\d+}")
    public String deleteCommentAction(@AuthenticationPrincipal CurrentUser currentUser,
                                    @PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (commentOptional.isPresent()){
            if(commentOptional.get().getUser().getId() == currentUser.getUser().getId()){
                Long tripId = commentOptional.get().getTrip().getId();
                commentService.deleteComment(commentOptional.get());
                return "redirect:/app/trip/"+tripId;
            }else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        return "app/comment/delete";
    }
}
