package ru.reboot.hotel.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.room.RoomType;

import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    RoomType findRoomTypeById(Long id);
}
