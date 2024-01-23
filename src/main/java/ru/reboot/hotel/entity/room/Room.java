package ru.reboot.hotel.entity.room;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import ru.reboot.hotel.entity.booking.Booking;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room", schema = "public")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_seq")
    @SequenceGenerator(name = "room_id_seq", sequenceName = "room_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @Column(name = "name", nullable = false, length = 64)
    String roomName;

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Type(ListArrayType.class)
    @Column(
            name = "photos",
            columnDefinition = "text[]",
            nullable = false
    )
    List<String> roomPhotos;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "adds")
    Additional adds;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="id", nullable = false)
    Booking booking;

}
