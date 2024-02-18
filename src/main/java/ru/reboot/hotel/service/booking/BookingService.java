package ru.reboot.hotel.service.booking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.repository.booking.BookingRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getAllBookingTable(){
        return bookingRepository.getAllBookingTable();
    }

    public void deleteById(String id){
        bookingRepository.deleteById(Long.valueOf(id));
    }

}
