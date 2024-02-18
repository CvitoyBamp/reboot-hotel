package ru.reboot.hotel.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.user.HotelUser;
import java.util.Optional;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {
    Optional<HotelUser> findHotelUsersByEmail(String email);

    void deleteUserById(Long id);
    HotelUser findHotelUserById(Long id);
}
