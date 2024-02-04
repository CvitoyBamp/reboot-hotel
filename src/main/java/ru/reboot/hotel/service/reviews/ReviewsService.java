package ru.reboot.hotel.service.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

    private ReviewsRepository reviewsRepository;

    @Autowired
    public void setReviewsRepository(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getReviews() {
        return reviewsRepository.getReviewsTextAndUsernameOrderByUpdInsertTimestampDescLimit();
    }

    @Transactional
    public void addReview(Long userId, String comment, Short rating) {
        Reviews reviews = new Reviews();
        reviews.setRating(rating);
        reviews.setComment(comment);
        reviews.setUserId(userId);
        reviewsRepository.save(reviews);
    }

}
