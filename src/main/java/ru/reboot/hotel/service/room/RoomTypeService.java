package ru.reboot.hotel.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.room.RoomType;
import ru.reboot.hotel.repository.room.RoomTypeRepository;

import java.util.List;

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
}
