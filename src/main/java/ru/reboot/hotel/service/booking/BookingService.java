package ru.reboot.hotel.service.booking;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.booking.BookingRepository;
import ru.reboot.hotel.service.user.HotelUserService;
import ru.reboot.hotel.utils.security.CustomUserDetails;
import ru.reboot.hotel.utils.security.SecurityUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service BookingService. For BookingRepository
 */
@Service
@AllArgsConstructor
public class BookingService {

    private HotelUserService hotelUserService;
    private BookingRepository bookingRepository;

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Long> getRoomsWhichIsLocked(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findRoomsIdWhichIsLockedForSelectedData(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public HotelUser getUserByUsername(String email) throws UsernameNotFoundException {

        HotelUser hotelUser = hotelUserService.findHotelUserByEmail(email);

        return hotelUser;

    }

}
