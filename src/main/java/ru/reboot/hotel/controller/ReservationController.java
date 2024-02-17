package ru.reboot.hotel.controller;

import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.service.booking.BookingService;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.utils.security.CustomUserDetails;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Controller for booking by user
 */
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/reservation")
@Controller
@SessionAttributes(names = {"booking", "id"})
public class ReservationController {

    RoomService roomService;

    RoomTypeService roomTypeService;

    ReviewsService reviewsService;

    BookingService bookingService;

    @PostMapping("/result")
    public String roomsPageAfterGettingDataFromClient(@ModelAttribute("booking") @Valid Booking booking,
                                                      BindingResult result,
                                                      Model model) {
        log.info(booking.toString());
        if (result.hasErrors()) {
            log.error("Error while get booking data: {}", result.getGlobalError());
        }

        if (booking.getStartDate().isBefore(LocalDate.now())
                || booking.getEndDate().isBefore(LocalDate.now())
                || booking.getEndDate().isBefore(booking.getStartDate())) {
            return "redirect:/certain_room?error=date?checked_room_id={id}";
        }

        bookingService.saveBooking(booking);
        roomService.updateRoomStatus(booking.getRoom().getId());

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
