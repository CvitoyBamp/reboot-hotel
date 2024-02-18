package ru.reboot.hotel.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.booking.Booking;

import java.time.LocalDate;
import java.util.*;

/**
 * Repository BookingRepository. Table name = "booking"
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select b.room.id from Booking as b where b.startDate between :startDate and :endDate or b.endDate between :startDate and :endDate")
    List<Long> findRoomsIdWhichIsLockedForSelectedData(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select b.hotelUser, b.id, b.note, b.room, b.startDate, b.endDate from Booking as b where b.hotelUser = :id")
    List<Map<String, String>> findBookingByUserId(@Param("id") long id);
  
    @Query(value = "SELECT new map(b.id as id, u.email as email, rt.roomName as roomName, b.startDate as startDate, b.endDate as endDate, b.note as note ) from Booking as b join b.hotelUser as u cross JOIN RoomType rt" )
    List<Map<String, String>> getAllBookingTable();

}
