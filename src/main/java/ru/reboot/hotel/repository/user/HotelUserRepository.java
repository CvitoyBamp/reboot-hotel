package ru.reboot.hotel.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.user.HotelUser;

@Repository
public interface HotelUserRepository extends JpaRepository<HotelUser, Long> {
}
