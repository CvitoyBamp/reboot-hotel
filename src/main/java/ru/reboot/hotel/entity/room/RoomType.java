package ru.reboot.hotel.entity.room;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.AuditEntity;

import java.util.List;

/**
 * Entity RoomType for Table name = "room_type"
 */
@Entity
@Table(name = "room_type")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomType extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable=false)
    Long id;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "room_name", nullable = false)
    String roomName;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "description", nullable = false)
    String description;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "max_adults", nullable = false)
    Short maxAdults;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "max_children", nullable = false)
    Short maxChildren;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "photo_src")
    String photoSRC;

    @Transient
    @OneToMany(mappedBy = "roomType", targetEntity = Room.class)
    List<Room> rooms;

}
