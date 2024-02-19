package ru.reboot.hotel.service.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.reviews.ReviewsRepository;
import ru.reboot.hotel.repository.user.HotelUserRepository;
import ru.reboot.hotel.repository.user.RolesRepository;

import java.time.LocalDate;
import java.util.*;

/**
 * Service HotelUserService. For HotelUserRepository
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelUserService {

    HotelUserRepository hotelUserRepository;

    ReviewsRepository reviewsRepository;

    RolesRepository rolesRepository;

    @Transactional
    public void createHotelUser(HotelUser hotelUser){
        hotelUserRepository.save(hotelUser);
    }
    @Transactional(readOnly = true)
    public List<HotelUser> getAllUsers() {
        return hotelUserRepository.findAll();
    }

    @Transactional(readOnly = true)
    public HotelUser findHotelUserByEmail(String email) throws UsernameNotFoundException {
        return hotelUserRepository.findHotelUsersByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User don't found"));
    }

    @Transactional
    public void deleteUserById(String id) throws UsernameNotFoundException {
        HotelUser user = hotelUserRepository.findHotelUserById(Long.valueOf(id));
        if (user != null) {
            // Получаем связанные записи в таблице "reviews"
            List<Reviews> relatedReviews = reviewsRepository.findByHotelUser(user)
                    .orElseThrow(() -> new UsernameNotFoundException("User don't found"));;
            reviewsRepository.deleteAll(relatedReviews);
            // Удаляем запись из таблицы "user"
            hotelUserRepository.delete(user);
        }
    }

}
