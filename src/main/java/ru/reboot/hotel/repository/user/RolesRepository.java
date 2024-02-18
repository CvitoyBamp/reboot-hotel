package ru.reboot.hotel.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.roles.Roles;

/**
 * Repository RolesRepository. Table name = "roles"
 */
@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
