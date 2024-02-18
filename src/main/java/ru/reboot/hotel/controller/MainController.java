package ru.reboot.hotel.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.service.booking.BookingService;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.service.user.CustomUserDetailsService;
import ru.reboot.hotel.service.user.HotelUserService;
import ru.reboot.hotel.service.user.RoleService;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main controller for registration/login and landing page
 */
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Controller
public class MainController {

    RoomService roomService;

    RoomTypeService roomTypeService;

    PhotoStoreService photoStoreService;

    ReviewsService reviewsService;

    HotelUserService hotelUserService;

    RoleService roleService;

    BookingService bookingService;

    PasswordEncoder passwordEncoder;

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
        model.addAttribute("reviews", reviewsService.getReviews());
        return "contact";
    }

    @PostMapping("/contact")
    public String postContactPage(@RequestParam(name="name", required=false, defaultValue="User") String name,
                                  @RequestParam(name="score", required=false, defaultValue= "5") Short score,
                                  @RequestParam(name="email", required=false) String email,
                                  @RequestParam(name="message", required=false, defaultValue="Отличный сервис") String message,
                                  Model model) {
        model.addAttribute("reviews", reviewsService.getReviews());
        for (HotelUser i: hotelUserService.getAllUsers()){
            if (i.getEmail().equals(email)){
                reviewsService.addReview(i, message, score);
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
    public String roomsPageAfterGettingDataFromClient(@RequestParam(value = "inData") @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE) LocalDate inData,
                                                      @RequestParam(value = "outData") @DateTimeFormat(pattern = "dd/MM/yyyy", iso = DateTimeFormat.ISO.DATE) LocalDate outData,
                                                      @RequestParam(value = "adults", defaultValue = "0") Integer adults,
                                                      @RequestParam(value = "kids", defaultValue = "0") Integer kids,
                                                      Model model) {
        List<Map<String, String>> rooms = new ArrayList<>();
        List<Long> ids = bookingService.getRoomsWhichIsLocked(inData, outData);
        if (ids.isEmpty()) {
            rooms = roomService.getRoomsAfterGettingData(adults, kids);
        } else {
            rooms = roomService.findRoomsWhereIdNotInList(ids, adults, kids);
        }
        log.info(ids.toString());
        log.info(rooms.toString());
        model.addAttribute("rooms", rooms);
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "rooms";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("hotelUser", new HotelUser());
        return "register";
    }

    @PostMapping("/register")
    public String loginPageAfterGettingDataFromRegister(@ModelAttribute("hotelUser") @Valid HotelUser hotelUser,
                                                        BindingResult result,
                                                        Model model){
        if (result.hasErrors()) {
            log.error(result.getAllErrors().toString());
            model.addAttribute("hotelUser", hotelUser);
            return "redirect:/register?error=true";
        }

        hotelUser.setRoles(roleService.getRoles().get(1));
        hotelUser.setPassword(passwordEncoder.encode(hotelUser.getPassword()));
        log.info(hotelUser.toString());
        hotelUserService.createHotelUser(hotelUser);
        return "login";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping("/personalArea")
    public String register(Model model) {
        model.addAttribute("hotelUser", new HotelUser());
        return "register";
    }

    @PostMapping("/personal")
    public String personal(@RequestParam(name="email", required=false) String email, Model model) {
        model.addAttribute("hotelUser", bookingService.getUserByUsername(email));
        return "personal_area";
    }

}
