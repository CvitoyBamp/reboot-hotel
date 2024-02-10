package ru.reboot.hotel.entity.room;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.reviews.Reviews;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room_type", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomType extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable=false)
    Long id;

    @Column(name = "room_name", nullable = false)
    String roomName;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "max_adults", nullable = false)
    Short maxAdults;

    @Column(name = "max_children", nullable = false)
    Short maxChildren;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "adds")
//    Additional adds;

    @Column(name = "photo_src")
    String photoSRC;

    @OneToMany(mappedBy = "roomTypeId", targetEntity = Room.class)
    List<Room> rooms;

}
