package ru.reboot.hotel.entity.room;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.AuditEntity;

@Entity
@Table(name = "photo_store")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhotoStore extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_store_id_seq")
    @SequenceGenerator(name = "photo_store_id_seq", sequenceName = "photo_store_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @EqualsAndHashCode.Include
    @Column(name = "photo_name", nullable = false, unique = true)
    String photoName;

    @EqualsAndHashCode.Include
    @Column(name = "photo", nullable = false, unique = true)
    String photo;
}
