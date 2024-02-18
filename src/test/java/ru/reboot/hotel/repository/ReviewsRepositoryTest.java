package ru.reboot.hotel.repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.service.reviews.ReviewsService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DataJpaTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:/updateSequence.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class ReviewsRepositoryTest {

    Reviews reviewUser1;
    Reviews reviewUser2;
    Reviews reviewUser3;
    Reviews reviewUser4;

    HotelUser hotelUser;
    ReviewsService reviewsService;

    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void initReview() {
        hotelUser = new HotelUser("Test", new BCryptPasswordEncoder().encode("123456"), "test@bk.ru", LocalDate.of(1997, 3, 13), "80001001010", new Roles("USER"));
        reviewUser1 = new Reviews("Тестовый текст 1", (short) 5, hotelUser);
        reviewUser2 = new Reviews("Тестовый текст 2", (short) 4, hotelUser);
        reviewUser3 = new Reviews("Тестовый текст 3", (short) 5, hotelUser);
        reviewUser4 = new Reviews("Тестовый текст 4", (short) 4, hotelUser);

        reviewsRepository.save(reviewUser1);
        reviewsRepository.save(reviewUser2);
        reviewsRepository.save(reviewUser3);
        reviewsRepository.save(reviewUser4);
    }

    @AfterEach
    void deleteReviewUser() {
        testEntityManager.detach(hotelUser);
        testEntityManager.detach(reviewUser1);
        testEntityManager.detach(reviewUser2);
        testEntityManager.detach(reviewUser3);
        testEntityManager.detach(reviewUser4);
    }

    @Test
    @DisplayName("Отображаем только 3 отзыва")
    void first3RowsOnly() {
        List<Map<String, String>> mapList = reviewsRepository.getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimit();
        assertEquals(3, mapList.size());
        for (Map i:  mapList){
            System.out.println(i.toString());
        }
    }

    @Test
    @DisplayName("Проверка метода ReviewsService ")
    void reviewsServiceTest() {
        List<Map<String, String>> mapList = reviewsRepository.getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimit();
        reviewsService = new ReviewsService(reviewsRepository );
        assertEquals(reviewsService.getReviews(), mapList);
    }

    @Test
    @DisplayName("Проверка новой записи на полное совпадение.")
    void reviewEqualToStringTest() {
        assertThat(testEntityManager.find(Reviews.class, reviewUser1.getId())).hasToString(String.valueOf(reviewUser1));
    }
}
