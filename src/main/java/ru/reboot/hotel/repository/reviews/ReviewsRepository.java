package ru.reboot.hotel.repository.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.reviews.Reviews;

import java.util.List;
import java.util.Map;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query(value = "SELECT new map(U.name as name, R.comment as comment) FROM Reviews R JOIN HotelUser U ON R.userId = U.id WHERE R.rating >= 4 ORDER BY R.updInsertTimestamp DESC LIMIT 3")
    List<Map<String, String>> getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimit();
}
