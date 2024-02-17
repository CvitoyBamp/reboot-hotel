package ru.reboot.hotel.service.booking;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.repository.booking.BookingRepository;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

}
