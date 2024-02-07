package ru.reboot.hotel.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/reservation")
@Controller
public class ReservationController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;

    private ReviewsService reviewsService;
    @GetMapping("/")
    public String reservationPage(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());

        List<String> options = new ArrayList<String>();
        for (int i = 0; i < roomService.getAllRooms().size(); i++) {
            options.add(roomService.getRoomsForReservation().get(i).toString());

        }
        model.addAttribute("options", options);

        model.addAttribute("reviews", reviewsService.getReviews());
        return "reservation_from_scratch";
    }

    @PostMapping("/result")
    public String roomsPageAfterGettingDataFromClient(@RequestParam(value = "inData") LocalDate inData,
                                                      @RequestParam(value = "outData") LocalDate outData,
                                                      @RequestParam(value = "message") String message,
                                                      @RequestParam(value = "name") String name,
                                                      @RequestParam(value = "phone") String phone,
                                                      @RequestParam(value = "email") String email,
                                                      @RequestParam(value = "room") String room,
                                                      Model model) {
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
