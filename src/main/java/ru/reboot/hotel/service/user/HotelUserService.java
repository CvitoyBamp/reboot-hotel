package ru.reboot.hotel.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.booking.BookingRepository;
import ru.reboot.hotel.repository.user.HotelUserRepository;

@Service
public class HotelUserService {
    private HotelUserRepository hotelUserRepository;

    @Autowired
    public void setHotelUserRepository(HotelUserRepository hotelUserRepository) {
        this.hotelUserRepository = hotelUserRepository;
    }

    public HotelUser createHotelUser(HotelUser hotelUser){
        return hotelUserRepository.save(hotelUser);
    }
}
