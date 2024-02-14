package ru.reboot.hotel.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.reboot.hotel.configuration.HotelWebSecurityConfig;
import ru.reboot.hotel.repository.user.HotelUserRepository;
import ru.reboot.hotel.service.booking.BookingService;
import ru.reboot.hotel.service.reviews.ReviewsService;
import ru.reboot.hotel.service.room.PhotoStoreService;
import ru.reboot.hotel.service.room.RoomService;
import ru.reboot.hotel.service.room.RoomTypeService;
import ru.reboot.hotel.service.user.CustomUserDetailsService;
import ru.reboot.hotel.service.user.RoleService;

@WebMvcTest
@ContextConfiguration(classes=
        {
                AdminController.class,
                MainController.class,
                RoomsController.class,
                ReservationController.class,
                HotelWebSecurityConfig.class
        })
public class ControllerTest {
    /**
     * Unit-тесты для слоя Controller
     */

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService service;

    @MockBean
    private RoomTypeService roomTypeService;

    @MockBean
    private PhotoStoreService photoStoreService;

    @MockBean
    private ReviewsService reviewsService;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private HotelUserRepository hotelUserRepository;

    @Test
    @DisplayName("Check '/' page")
    void checkFirstPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    @DisplayName("Check 'Index' page")
    void checkIndexPage() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check 'Rooms' page")
    void checkRoomsPage() throws Exception {
        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check 'Reviews' page")
    void checkReviewsPage() throws Exception {
        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check 'Contact' page")
    void checkContactPage() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check 'About' page")
    void checkAboutPage() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check 'Reservation' page")
    void checkReservationPage() throws Exception {
        mockMvc.perform(get("/reservation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @DisplayName("Check 'Admin' page")
    void checkAdminPage() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @DisplayName("Check 'AdminRooms' page")
    void checkAdminRoomsPage() throws Exception {
        mockMvc.perform(get("/admin/rooms"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @DisplayName("Check 'ReservationFromScratch' page")
    void checkReservationFromScratchPage() throws Exception {
        mockMvc.perform(get("/reservationPage"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Check 'certainRoom' page")
    void checkReservationCertainRoomPage() throws Exception {
        mockMvc.perform(get("/reservation_certain_room"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Check 'Register' page")
    void checkRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

}
