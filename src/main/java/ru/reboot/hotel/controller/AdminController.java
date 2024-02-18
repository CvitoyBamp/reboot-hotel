package ru.reboot.hotel.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.reboot.hotel.service.booking.BookingService;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.user.HotelUserService;

import java.time.LocalDate;

@Slf4j
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
//@RequestMapping("/admin")
public class AdminController {

    private BookingService bookingService;
    private HotelUserService hotelUserService;
    private ReviewsService reviewsService;

    @GetMapping("/bookings_admin")
    public String bookingAdminPage(Model model) {
        model.addAttribute("booking", bookingService.getAllBookingTable());
        return "bookings_admin";
    }

    @PostMapping("/booking_admin_delete")
    public String deleteBookingsById(@RequestParam("id") String id) {
           bookingService.deleteById(id);
        return "redirect:bookings_admin";
    }

    @GetMapping("/users_admin")
    public String usersAdminPage(Model model) {
        model.addAttribute("users", hotelUserService.getAllUsers());
        return "users_admin";
    }
    @PostMapping("/users_admin_delete")
    public String deleteUserById(@RequestParam("id") String id) {
     //   hotelUserService.deleteUserById(id);
        return "redirect:users_admin";
    }

    @PostMapping("/save_user")
    public String saveNewUser( @RequestParam("id") Long id,
                               @RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("phone") String phone,
                              @RequestParam("birthday") LocalDate birthday                                    ) {
        hotelUserService.saveNewUser(id, name, email,birthday, phone  );
        return "redirect:users_admin";
    }

    @GetMapping("/reviews_admin")
    public String reviewsAdminPage(Model model) {
        model.addAttribute("reviews", reviewsService.getAllReviewsTable());
        return "reviews_admin";
    }

    @PostMapping("/reviews_admin_delete")
    public String deleteReviewsById(@RequestParam("id") String id) {
      //  reviewsService.deleteReviewById(id);
        return "redirect:reviews_admin";
    }

}
