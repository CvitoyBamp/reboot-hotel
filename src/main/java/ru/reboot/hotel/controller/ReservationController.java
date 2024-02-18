package ru.reboot.hotel.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.service.booking.BookingService;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.utils.security.CustomUserDetails;

import javax.validation.Valid;
import java.time.LocalDate;

/**
 * Controller for booking by user
 */

@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/reservation")
@Controller
@SessionAttributes("booking")
public class ReservationController {

    RoomService roomService;

    RoomTypeService roomTypeService;

    ReviewsService reviewsService;

    BookingService bookingService;

    @PostMapping("/result")
    public String roomsPageAfterGettingDataFromClient(@ModelAttribute("booking") @Valid Booking booking,
                                                      BindingResult result,
                                                      Model model) {
        if (result.hasErrors()) {
            log.error("Error while get booking data: {}", result.getGlobalError());
            return "redirect:/reservation/result?error=binding";
        }

        if (booking.getStartDate().isBefore(LocalDate.now())
                || booking.getEndDate().isBefore(LocalDate.now())
                || booking.getEndDate().isBefore(booking.getStartDate())) {
            return "redirect:/reservation/result?error=date";
        }

        bookingService.saveBooking(booking);

        model.addAttribute("email", booking.getHotelUser().getEmail());

        return "reservation_result";
    }

    @GetMapping("/result")
    public String getResult(@RequestParam("error") String error,
                            Model model) {
        model.addAttribute("error", error);
        return "reservation_result";
    }

    @PostMapping("/certain_room")
    public String reservationPostCertainRoom(@RequestParam(value = "checked_room_id") String id,
                                             Authentication authentication,
                                             Model model) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        log.info(customUserDetails.toString());
        Booking booking = new Booking();
        booking.setHotelUser(customUserDetails.getHotelUser());
        booking.setRoom(roomService.getRoomByRoomId(id));
        model.addAttribute("booking", booking);
        model.addAttribute("reviews", reviewsService.getReviews());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "reservation_certain_room";
    }

}
