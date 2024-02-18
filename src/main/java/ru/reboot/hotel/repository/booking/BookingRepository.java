package ru.reboot.hotel.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.booking.Booking;

import java.util.List;
import java.util.Map;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT new map(b.id as id, u.email as email, rt.roomName as roomName, b.startDate as startDate, b.endDate as endDate, b.note as note ) from Booking as b join b.hotelUser as u cross JOIN RoomType rt" )
    List<Map<String, String>> getAllBookingTable();
}
