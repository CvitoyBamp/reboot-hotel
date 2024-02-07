package ru.reboot.hotel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.room.PhotoStore;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.repository.room.PhotoStoreRepository;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.service.user.HotelUserService;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MainController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;

    private PhotoStoreService photoStoreService;

    private ReviewsService reviewsService;

    private HotelUserService hotelUserService;

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

    @Autowired
    public void setHotelUserService(HotelUserService hotelUserService){
        this.hotelUserService = hotelUserService;
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
        return "reservation";
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

    @GetMapping("/reviews")
    public String reviewsPage(Model model) {
        return "fragments/reviews";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String loginPageAfterGettingDataFromRegister(@RequestParam(value = "name") String name,
                                                        @RequestParam(value = "birthday") LocalDate birthday,
                                                        @RequestParam(value = "phone") String phone,
                                                        @RequestParam(value = "email") String email,
                                                        @RequestParam(value = "password") String password,
                                                        @RequestParam(value = "repeatPassword") String repeatPassword,
                                                        Model model){
            model.addAttribute("hotelUsers", hotelUserService.createHotelUser(new HotelUser( name,
                                                                password, email, birthday, phone, new Roles("USER"))));
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }

}
