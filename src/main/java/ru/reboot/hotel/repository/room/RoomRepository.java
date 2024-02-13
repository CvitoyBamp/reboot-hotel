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

    @Query("SELECT new map(r.roomTypeId as room_type_id, MIN(r.pricePerDay) as price) FROM Room r GROUP BY r.roomTypeId order by r.roomTypeId")
    List<Map<String, String>> findMinPricePerDayByRoomTypeId();

    @Query("SELECT new map(r.roomTypeId as room_type_id, MIN(r.pricePerDay) as price) FROM Room r where r.isLocked = false GROUP BY r.roomTypeId order by r.roomTypeId")
    List<Map<String, String>> findFreeRooms();

    @Query("SELECT new map(r.roomTypeId as room_type_id, MIN(r.pricePerDay) as price) FROM Room r join RoomType rt on r.roomTypeId = rt.id where r.isLocked = false and rt.maxAdults >= :adults and rt.maxChildren >= :kids GROUP BY r.roomTypeId order by r.roomTypeId")
    List<Map<String, String>> findRoomsForReservationData(@Param("adults") int adults, @Param("kids") int kids);

    @Query("SELECT new map(r.id as room_id, r.pricePerDay as price, rt.roomName, rt.description) FROM Room r join RoomType rt on r.roomTypeId = rt.id where r.isLocked = false order by r.pricePerDay")
    List<Map<String, String>> findRoomsForReservation();

    @Query("SELECT new map(r.id as room_id, r.pricePerDay as price, rt.roomName, rt.description) FROM Room r join RoomType rt on r.roomTypeId = rt.id where r.id = :roomId order by r.id")
    Map<String, String> findRoomByRoomId(@Param("roomId") String roomId);

    void deleteRoomById(Long id);
}
