package ru.reboot.hotel.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.repository.room.RoomRepository;

import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

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

}
