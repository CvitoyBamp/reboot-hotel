package ru.reboot.hotel.entity.room;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.AuditEntity;

@Entity
@Table(name = "photo_store", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhotoStore extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_store_id_seq")
    @SequenceGenerator(name = "photo_store_id_seq", sequenceName = "photo_store_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @Column(name = "photo", nullable = false, unique = true)
    byte[] photo;
}
