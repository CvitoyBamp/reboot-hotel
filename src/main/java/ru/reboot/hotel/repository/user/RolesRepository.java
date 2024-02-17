package ru.reboot.hotel.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.user.HotelUser;

import java.util.*;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
