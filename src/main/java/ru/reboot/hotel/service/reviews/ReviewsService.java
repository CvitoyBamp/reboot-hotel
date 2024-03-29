package ru.reboot.hotel.service.reviews;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import java.util.*;

/**
 * Service ReviewsService. For ReviewsRepository
 */
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

    @Transactional(readOnly = true)
    public List<Map<String, String>> getAllReviewsTable(){
        return reviewsRepository.getAllReviewsTable();
    }

    @Transactional
    public void deleteReviewById(String id){
        reviewsRepository.deleteReviewById(Long.valueOf(id));
    }

    @Transactional(readOnly = true)
    public List<Reviews> getReviewsByHotelUser(HotelUser hotelUser) throws UsernameNotFoundException {
        return reviewsRepository.findByHotelUser(hotelUser)
                .orElseThrow(() -> new UsernameNotFoundException("User don't found"));
    }

}
