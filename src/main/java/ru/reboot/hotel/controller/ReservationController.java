package ru.reboot.hotel.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;

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
@RequestMapping("/reservation")
@Controller
public class ReservationController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;

    private ReviewsService reviewsService;
    @GetMapping
    public String reservationPage(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("rooms", roomService.getRoomsForReservation());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());

        model.addAttribute("reviews", reviewsService.getReviews());
        return "reservation_from_scratch";
    }

    @PostMapping("/result")
    public String roomsPageAfterGettingDataFromClient(@Valid @ModelAttribute("booking") Booking booking,
                                                      BindingResult result,
                                                      Model model) {
        if (result.hasErrors()) {
            log.error("Error while get booking data: {}", result.getGlobalError());
        }
        return "reservation_result";
    }

    @GetMapping("/result")
    public String reservationResult(Model model) {
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "reservation_result";
    }

    @GetMapping("/certain_room")
    public String reservationGetCertainRoom(Model model) {
        model.addAttribute("rooms", roomService.getFreeRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "reservation_certain_room";
    }

    @PostMapping("/certain_room")
    public String reservationPostCertainRoom(@RequestParam(value = "checked_room_id") String id, Model model) {
        model.addAttribute("rooms", roomService.getRoomByRoomId(id));
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "reservation_certain_room";
    }

}
