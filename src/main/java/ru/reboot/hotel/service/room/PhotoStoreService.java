package ru.reboot.hotel.service.room;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.room.PhotoStore;
import ru.reboot.hotel.repository.room.PhotoStoreRepository;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service PhotoStoreService. For PhotoStoreRepository
 */
@Service
@AllArgsConstructor
public class PhotoStoreService {
    private PhotoStoreRepository photoStoreRepository;

    @Cacheable("photos")
    @Transactional(readOnly = true)
    public Map<String, String> getAllPhotos() {
        return photoStoreRepository.findAll().stream().collect(
                Collectors.toMap(PhotoStore::getPhotoName, PhotoStore::getPhoto)
        );
    }


}
