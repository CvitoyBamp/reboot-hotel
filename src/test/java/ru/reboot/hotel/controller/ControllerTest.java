package ru.reboot.hotel.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
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
import ru.reboot.hotel.service.user.HotelUserService;
import ru.reboot.hotel.service.user.RoleService;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {MainController.class, AdminController.class, ReservationController.class, RoomsController.class})
@AutoConfigureMockMvc
public class ControllerTest {
    /**
     * Unit-тесты для слоя Controller
     */

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RoomService service;

    @MockBean
    RoomTypeService roomTypeService;

    @MockBean
    PhotoStoreService photoStoreService;

    @MockBean
    ReviewsService reviewsService;

    @MockBean
    BookingService bookingService;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    RoleService roleService;

    @MockBean
    HotelUserService hotelUserService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    ObjectMapper objectMapper;

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
                .andExpect(view().name("about"));
    }

    @Test
    @DisplayName("Check 'Reservation' page")
    void checkReservationPage() throws Exception {
        mockMvc.perform(get("/reservation"))
                .andExpect(view().name("reservation_from_scratch"));
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
                .andExpect(view().name("register"));
    }

}
