package ru.reboot.hotel.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.roles.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    // List<Roles> findByHotelUsers(HotelUser hotelUser);

}
