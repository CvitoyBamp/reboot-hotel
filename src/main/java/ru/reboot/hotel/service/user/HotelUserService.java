package ru.reboot.hotel.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.user.HotelUserRepository;
import java.util.List;

/**
 * Service HotelUserService. For HotelUserRepository
 */
@Service
@AllArgsConstructor
public class HotelUserService {

    HotelUserRepository hotelUserRepository;

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

}
