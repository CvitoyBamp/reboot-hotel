package ru.reboot.hotel.service.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.room.PhotoStore;
import ru.reboot.hotel.repository.room.PhotoStoreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhotoStoreService {

    private PhotoStoreRepository photoStoreRepository;

    @Autowired
    public void setPhotoStoreRepository(PhotoStoreRepository photoStoreRepository) {
        this.photoStoreRepository = photoStoreRepository;
    }

    @Transactional(readOnly = true)
    public Map<String, String> getAllPhotos() {
        return photoStoreRepository.findAll().stream().collect(
                Collectors.toMap(PhotoStore::getPhotoName, p -> Arrays.toString(p.getPhoto()))
        );
    }


}
