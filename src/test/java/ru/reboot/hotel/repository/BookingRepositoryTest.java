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
import org.springframework.test.context.jdbc.Sql;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.room.RoomType;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.booking.BookingRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:/updateSequence.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class BookingRepositoryTest {
    Booking booking;

    HotelUser hotelUser;

    Room room;

    RoomType roomType;

    Set<Room> rooms = new HashSet<>();

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TestEntityManager testEntityManager;
    @BeforeEach
    void initRole() {
        roomType = new RoomType("Luks", "Super-puper", (short) 1, (short) 1, "");
        room = new Room(BigDecimal.valueOf(150), false,roomType);
        hotelUser = new HotelUser("Test", new BCryptPasswordEncoder().encode("123456"), "test@bk.ru", LocalDate.of(1997, 3, 13), "80001001010", new Roles("USER"));
        booking = new Booking(hotelUser, room, LocalDate.now(), LocalDate.now(), "");
    }

    @AfterEach
    void deleteBooking() {
        testEntityManager.detach(roomType);
        testEntityManager.detach(hotelUser);
        testEntityManager.detach(booking);
        testEntityManager.detach(room);

    }

    @Test
    @DisplayName("Add new booking")
    void addRole() {
        bookingRepository.save(booking);
        assertThat(testEntityManager.find(Booking.class, booking.getId())).isEqualTo(booking);
    }
}
