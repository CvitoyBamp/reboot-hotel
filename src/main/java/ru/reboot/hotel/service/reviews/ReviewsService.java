package ru.reboot.hotel.service.reviews;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewsService {

    private ReviewsRepository reviewsRepository;

    @Transactional(readOnly = true)
    public List<Map<String, String>> getReviews() {
        return reviewsRepository.getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimit();
    }

    @Transactional
    public void addReview(HotelUser hotelUser, String comment, Short rating) {
        Reviews reviews = new Reviews();
        reviews.setRating(rating);
        reviews.setComment(comment);
        reviews.setHotelUser(hotelUser);
        reviewsRepository.save(reviews);
    }

}
