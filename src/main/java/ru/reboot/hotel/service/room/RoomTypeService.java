package ru.reboot.hotel.service.room;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.room.RoomType;
import ru.reboot.hotel.repository.room.RoomTypeRepository;
import java.util.List;

/**
 * Service RoomTypeService. For RoomTypeRepository
 */
@Service
@AllArgsConstructor
public class RoomTypeService {

    private RoomTypeRepository roomTypeRepository;

    @Transactional(readOnly = true)
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    public void saveRoomsType(RoomType roomType) {
        roomTypeRepository.save(roomType);
    }

    public void deleteRoomsType(String id) {
        roomTypeRepository.deleteById(Long.valueOf(id));
    }

    public RoomType getRoomTypeById(String id) {
        return roomTypeRepository.findRoomTypeById(Long.valueOf(id));
    }

}
