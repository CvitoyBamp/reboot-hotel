package ru.reboot.hotel.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.service.user.CustomUserDetailsService;
import ru.reboot.hotel.utils.CustomUserDetails;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Controller
public class MainController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;

    private PhotoStoreService photoStoreService;

    private ReviewsService reviewsService;

    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/")
    public String getMainPage() {
        return "redirect:/index";
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

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "contact";
    }

    @PostMapping("/contact")
    public String postContactPage(@RequestParam(name="name", required=false, defaultValue="User") String name,
                                  @RequestParam(name="score", required=false, defaultValue= "5") Short score,
                                  @RequestParam(name="email", required=false) String email,
                                  @RequestParam(name="message", required=false, defaultValue="Отличный сервис") String message,
                                  Model model) {
        model.addAttribute("reviews", reviewsService.getReviews());
        for (HotelUser i: customUserDetailsService.getAllUsers()){
            if (i.getEmail().equals(email)){
                Long userId =  i.getId();
                reviewsService.addReview(userId, message, score);

                return "fragments/reviews";
            }
        }
//        Нужно всплывающее окно == "Вы не жили у нас"
        return "contact";
    }

    @GetMapping("/rooms")
    public String roomsPage(Model model) {
        model.addAttribute("rooms", roomService.getFreeRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "rooms";
    }

    @GetMapping("/reviews")
    public String reviewsPage(Model model) {
        model.addAttribute("reviews", reviewsService.getReviews());
        return "fragments/reviews";
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

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("hotelUser", new HotelUser());
        return "register";
    }

    @PostMapping("/register")
    public String loginPageAfterGettingDataFromRegister(@Valid @ModelAttribute("hotelUser")HotelUser hotelUser,
                                                        BindingResult result,
                                                        Model model){
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            model.addAttribute("hotelUser", hotelUser);
            return "register";
        }

        hotelUser.setRoleId(2);
        customUserDetailsService.createHotelUser(hotelUser);
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping("/personalArea")
    public String register(Model model) {
        return "register";
    }

}
