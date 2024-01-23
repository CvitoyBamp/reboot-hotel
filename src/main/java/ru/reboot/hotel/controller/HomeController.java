package ru.reboot.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.service.room.RoomService;

@Controller
public class HomeController {

    private RoomService roomService;

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/main")
    public String getRoomsPage(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new HotelUser());
        return "registration";
    }


}
