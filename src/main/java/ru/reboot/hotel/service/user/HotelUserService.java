package ru.reboot.hotel.service.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.reviews.Reviews;
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
    public void deleteUserById(String id) {
        // hotelUserRepository.deleteUserById(Long.valueOf(id));

        HotelUser user = hotelUserRepository.findHotelUserById(Long.valueOf(id));
        if (user != null) {
            // Получаем связанные записи в таблице "reviews"
            List<Reviews> relatedReviews = reviewsRepository.findByHotelUser(user);
            reviewsRepository.deleteAll(relatedReviews);
//            List<Roles> relatedRoles = rolesRepository.findByHotelUsers(user);
//            for (Roles role : relatedRoles) {
//                rolesRepository.delete(role);
//            }
            // Удаляем запись из таблицы "user"
            hotelUserRepository.delete(user);
        }
    }

    @Transactional
    public void saveNewUser(Long id, String name, String email,  LocalDate birthday, String phone){
        HotelUser user = new HotelUser(id, name, email, birthday, phone );
        user.setPassword("user");
        hotelUserRepository.save(user);
    }

}
