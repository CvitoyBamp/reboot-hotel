package ru.reboot.hotel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.reboot.hotel.entity.room.PhotoStore;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.room.RoomType;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.repository.room.PhotoStoreRepository;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MainController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;

    private PhotoStoreService photoStoreService;

    private ReviewsService reviewsService;

    @Autowired
    public void setPhotoStoreService(PhotoStoreService photoStoreService) {
        this.photoStoreService = photoStoreService;
    }

    @Autowired
    public void setRoomTypeService(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Autowired
    public void setReviewsService(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/index")
    public String getRoomsPage(Model model) {
        model.addAttribute("rooms", roomService.getUniqueRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        model.addAttribute("sliderPhotos", photoStoreService.getAllPhotos().entrySet().stream().filter(v -> v.getKey().contains("slider"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        model.addAttribute("reviews", reviewsService.getReviews());
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        return "about";
    }

    @GetMapping("/reservation")
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

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "contact";
    }

    @GetMapping("/rooms")
    public String roomsPage(Model model) {
        model.addAttribute("rooms", roomService.getFreeRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "rooms";
    }

    @PostMapping("/checkFreeData")
    public String roomsPageAfterGettingDataFromClient(@RequestParam(value = "inData", required = false) LocalDate inData,
                                                      @RequestParam(value = "outData", required = false) LocalDate outData,
                                                      @RequestParam(value = "adults") Integer adults,
                                                      @RequestParam(value = "kids") Integer kids,
                                                      Model model) {
        model.addAttribute("rooms", roomService.getRoomsAfterGettingData(adults, kids));
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "rooms";
    }

    @PostMapping("/reservation_result")
    public String roomsPageAfterGettingDataFromClient(@RequestParam(value = "inData") LocalDate inData,
                                                      @RequestParam(value = "outData") LocalDate outData,
                                                      @RequestParam(value = "message") String message,
                                                      @RequestParam(value = "name") String name,
                                                      @RequestParam(value = "phone") String phone,
                                                      @RequestParam(value = "email") String email,
                                                      @RequestParam(value = "option") String option,
                                                      Model model) {
        return "reservation_result";
    }

    @GetMapping("/reservation_result")
    public String reservationResult(Model model) {
        return "reservation_result";
    }



    @GetMapping("/personalArea")
    public String reviewsPage(Model model) {
        return "register";
    }

}
