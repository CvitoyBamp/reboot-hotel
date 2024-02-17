package ru.reboot.hotel.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.user.RolesRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleService {

    private RolesRepository rolesRepository;

    @Transactional(readOnly = true)
    public List<Roles> getRoles() {
        return rolesRepository.findAll();
    }

}
