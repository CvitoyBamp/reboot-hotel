package ru.reboot.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.reboot.hotel.entity.room.PhotoStore;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private RoomService roomService;

    private PhotoStoreService photoStoreService;

    @Autowired
    public void setPhotoStoreService(PhotoStoreService photoStoreService) {
        this.photoStoreService = photoStoreService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/index")
    public String getRoomsPage(Model model) {
        model.addAttribute("heroPhotos", photoStoreService.getAllPhotos().entrySet().stream().filter(v -> v.getKey().contains("hero"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        model.addAttribute("imgPhoto", photoStoreService.getAllPhotos().entrySet().stream().filter(v -> v.getKey().contains("img"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        return "about";
    }

    @GetMapping("/reservation")
    public String reservationPage(Model model) {
        return "reservation";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "contact";
    }

    @GetMapping("/rooms")
    public String roomsPage(Model model) {
        return "rooms";
    }

    @GetMapping("/reviews")
    public String reviewsPage(Model model) {
        return "components/reviews";
    }

}
