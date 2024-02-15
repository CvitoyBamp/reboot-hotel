package ru.reboot.hotel.reviews;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.service.reviews.ReviewsService;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@DataJpaTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewsTest {

    Reviews reviewUser1;
    Reviews reviewUser2;
    Reviews reviewUser3;
    Reviews reviewUser4;
    ReviewsService reviewsService;

    @Autowired
    ReviewsRepository reviewsRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void initReview() {
        reviewUser1 = new Reviews();
        reviewUser2 = new Reviews();
        reviewUser3 = new Reviews();
        reviewUser4 = new Reviews();

        reviewUser1.setId( 11l);
        reviewUser1.setRating( (short) 2);
        reviewUser1.setComment("Тестовый текст1");
        reviewUser1.setUserId(1l);

        reviewUser2.setRating( (short) 3);
        reviewUser2.setComment("Тестовый текст2");
        reviewUser2.setUserId(1l);

        reviewUser3.setRating( (short) 4);
        reviewUser3.setComment("Тестовый текст3");
        reviewUser3.setUserId(1l);

        reviewUser4.setRating( (short) 5);
        reviewUser4.setComment("Тестовый текст4");
        reviewUser4.setUserId(1l);

        reviewsRepository.save(reviewUser1);
        reviewsRepository.save(reviewUser2);
        reviewsRepository.save(reviewUser3);
        reviewsRepository.save(reviewUser4);
    }

    @AfterEach
    void deleteReviewUser() {
        testEntityManager.detach(reviewUser1);
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
