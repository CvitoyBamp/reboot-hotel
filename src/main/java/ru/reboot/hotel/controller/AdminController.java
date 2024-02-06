package ru.reboot.hotel.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;

@Slf4j
@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/admin")
public class AdminController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;
    @GetMapping("/rooms")
    public String getRoomsInspector(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "rooms_admin";
    }
}
