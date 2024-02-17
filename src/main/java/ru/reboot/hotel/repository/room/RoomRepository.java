package ru.reboot.hotel.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.room.Room;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT new map(rt.id as room_type_id, MIN(r.pricePerDay) as price) FROM Room as r join r.roomType as rt GROUP BY rt.id order by rt.id")
    List<Map<String, String>> findMinPricePerDayByRoomTypeId();

    @Query("SELECT new map(rt.id as room_type_id, MIN(r.pricePerDay) as price) FROM Room as r join r.roomType as rt where r.isLocked = false GROUP BY rt.id order by rt.id")
    List<Map<String, String>> findFreeRooms();

    @Query("SELECT new map(r.roomType.id as room_type_id, MIN(r.pricePerDay) as price) FROM Room as r join r.roomType as rt where r.isLocked = false and rt.maxAdults >= :adults and rt.maxChildren >= :kids GROUP BY r.roomType order by r.roomType.id")
    List<Map<String, String>> findRoomsForReservationData(@Param("adults") int adults, @Param("kids") int kids);

    @Query("SELECT new map(r.id as room_id, rt.roomName as roomName, rt.description as description, min(r.pricePerDay) as price) FROM Room as r join r.roomType as rt where r.isLocked = false GROUP BY r.id, rt.roomName, rt.description order by r.pricePerDay")
    List<Map<String, String>> findRoomsForReservation();

    @Query("SELECT new map(r.id as room_id, r.pricePerDay as price, rt.roomName, rt.description) FROM Room as r join r.roomType as rt where r.id = :roomId order by r.id")
    Map<String, String> findRoomByRoomId(@Param("roomId") String roomId);

    void deleteRoomById(Long id);
}
