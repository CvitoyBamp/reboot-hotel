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
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.booking.BookingRepository;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.repository.user.HotelUserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewsRepositoryTest {
    HotelUser hotelUser;

    Reviews review;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    HotelUserRepository hotelUserRepository;

    @Autowired
    TestEntityManager testEntityManager;
    @BeforeEach
    void initRole() {
        //hotelUser = new HotelUser(1000L, "Test", new BCryptPasswordEncoder().encode("123456"), "test@bk.ru", LocalDate.of(1997, 3, 13), "80001001010",1, Collections.EMPTY_LIST);
        review = new Reviews(100L, "Super Hotel", (short) 5, 1L);
       // hotelUserRepository.save(hotelUser);
        reviewsRepository.save(review);
    }

    @AfterEach
    void deleteBooking() {
        testEntityManager.detach(review);
        testEntityManager.detach(hotelUser);
    }

    @Test
    @DisplayName("Add new review")
    void addReview() {
        assertThat(testEntityManager.find(Reviews.class, review.getId())).isEqualTo(review);
    }

    @Test
    @DisplayName("Get username and comment")
    void getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimitTest() {
        reviewsRepository.save(review);
        var reviews = reviewsRepository.getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimit();
        assertThat(testEntityManager.find(Reviews.class, review.getId()).getComment()).isEqualTo(reviews.get(0).get("comment"));
    }
}
