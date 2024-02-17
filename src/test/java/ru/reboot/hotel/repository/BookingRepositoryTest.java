package ru.reboot.hotel.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.booking.BookingRepository;
import ru.reboot.hotel.repository.user.RolesRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookingRepositoryTest {
    Booking booking;

    HotelUser hotelUser;

    Room room;

    Set<Room> rooms = new HashSet<>();

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TestEntityManager testEntityManager;
    @BeforeEach
    void initRole() {
        room = new Room(100L, BigDecimal.valueOf(150), false, 100L);
        rooms.add(room);
        hotelUser = new HotelUser(1000L, "Test", new BCryptPasswordEncoder().encode("123456"), "test@bk.ru", LocalDate.of(1997, 3, 13), "80001001010",1, Collections.EMPTY_LIST);
        booking = new Booking(100L, hotelUser, rooms, LocalDate.now(), LocalDate.now(), "");
    }

    @AfterEach
    void deleteBooking() {
        testEntityManager.detach(booking);
        testEntityManager.detach(room);
        testEntityManager.detach(hotelUser);
    }

    @Test
    @DisplayName("Add new booking")
    void addRole() {
        bookingRepository.save(booking);
        assertThat(testEntityManager.find(Booking.class, booking.getId())).isEqualTo(booking);
    }
}
