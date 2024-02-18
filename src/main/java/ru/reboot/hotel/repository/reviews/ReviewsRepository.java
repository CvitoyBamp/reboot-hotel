package ru.reboot.hotel.repository.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.user.HotelUser;

import java.util.List;
import java.util.Map;

/**
 * Repository ReviewsRepository. Table name = "reviews"
 */
@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

    @Query(value = "SELECT new map(u.name as name, r.comment as comment) from Reviews as r join r.hotelUser as u where r.rating >= 4 order by r.updInsertTimestamp desc limit 3")
    List<Map<String, String>> getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimit();

    @Query(value = "SELECT new map( r.id as id, u.email as email, r.comment as comment, r.rating as rating) from Reviews as r join r.hotelUser as u")
    List<Map<String, String>> getAllReviewsTable();

    List<Reviews> findByHotelUser(HotelUser hotelUser);

    void deleteReviewById(Long id);

}
