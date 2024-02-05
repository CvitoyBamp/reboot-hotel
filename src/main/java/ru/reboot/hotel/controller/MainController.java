package ru.reboot.hotel.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.reboot.hotel.entity.room.PhotoStore;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.repository.room.PhotoStoreRepository;
import ru.reboot.hotel.repository.user.HotelUserRepository;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.service.user.UserService;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Controller
public class MainController {

    private RoomService roomService;

    private RoomTypeService roomTypeService;

    private PhotoStoreService photoStoreService;

    private ReviewsService reviewsService;

    private UserService userService;

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

}
