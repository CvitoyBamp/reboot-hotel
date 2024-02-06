package ru.reboot.hotel.service.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.user.HotelUserRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class UserService {

    private HotelUserRepository hotelUserRepository;


    @Transactional(readOnly = true)
    public List<HotelUser> getAllUser() {

        return hotelUserRepository.findAll();
    }

}
