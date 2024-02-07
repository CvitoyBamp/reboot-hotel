package ru.reboot.hotel.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.room.PhotoStore;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.room.RoomType;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.repository.room.PhotoStoreRepository;
import ru.reboot.hotel.repository.user.HotelUserRepository;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.service.user.HotelUserService;
import ru.reboot.hotel.service.user.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class MainController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;

    private PhotoStoreService photoStoreService;

    private ReviewsService reviewsService;

    private HotelUserService hotelUserService;

    private UserService userService;

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

    @PostMapping("/contact")
    public String postContactPage(@RequestParam(name="name", required=false, defaultValue="User") String name,
                                  @RequestParam(name="score", required=false, defaultValue= "5") Short score,
                                  @RequestParam(name="email", required=false) String email,
                                  @RequestParam(name="message", required=false, defaultValue="Отличный сервис") String message,Model model) {

        for (HotelUser i: userService.getAllUser()){
            if (i.getEmail().equals(email)){
                Long userId =  i.getId();
                String comment = message;
                Short rating = score;
                reviewsService.addReview(userId, comment, rating);

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
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "reservation_result";
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

    @GetMapping("/reservation_certain_room")
    public String reservationGetCertainRoom(Model model) {
        model.addAttribute("rooms", roomService.getFreeRooms());
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "reservation_certain_room";
    }

    @PostMapping("/reservation_certain_room")
    public String reservationPostCertainRoom(@RequestParam(value = "checked_room_id") String id, Model model) {
        model.addAttribute("rooms", roomService.getRoomByRoomId(id));
        model.addAttribute("roomsType", roomTypeService.getAllRoomTypes());
        return "reservation_certain_room";
    }

    @GetMapping("/personalArea")
    public String register(Model model) {
        return "register";
    }

}
