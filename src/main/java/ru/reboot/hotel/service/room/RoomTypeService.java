package ru.reboot.hotel.service.room;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.room.RoomType;
import ru.reboot.hotel.repository.room.RoomTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomTypeService {

    private RoomTypeRepository roomTypeRepository;

    @Autowired
    public void setRoomTypeRepository(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

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

}
