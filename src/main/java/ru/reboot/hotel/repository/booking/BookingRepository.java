package ru.reboot.hotel.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.booking.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
