package ru.reboot.hotel.repository.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reboot.hotel.entity.room.PhotoStore;

@Repository
public interface PhotoStoreRepository extends JpaRepository<PhotoStore, Long> {
}
