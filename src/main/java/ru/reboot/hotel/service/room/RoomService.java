package ru.reboot.hotel.service.room;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.repository.room.RoomRepository;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RoomService {

    private RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getUniqueRooms() {
        return roomRepository.findMinPricePerDayByRoomTypeId();
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getFreeRooms() {
        return roomRepository.findFreeRooms();
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getRoomsAfterGettingData(int adults, int kids) {
        return roomRepository.findRoomsForReservationData(adults, kids);
    }

    @Transactional(readOnly = true)
    public List<Map<String, String>> getRoomsForReservation() {
        return roomRepository.findRoomsForReservation();
    }

    @Transactional(readOnly = true)
    public Map<String, String> getRoomByRoomId(String roomId) {
        return roomRepository.findRoomByRoomId(roomId);
    }

    @Transactional
    public void createNewRoom(Room room) {
        roomRepository.save(room);
    }

    @Transactional
    public void deleteRoomById(String id) {
        roomRepository.deleteRoomById(Long.valueOf(id));
    }

}
