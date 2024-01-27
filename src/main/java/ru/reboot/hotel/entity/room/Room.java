package ru.reboot.hotel.entity.room;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.roles.Roles;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "room", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_seq")
    @SequenceGenerator(name = "room_id_seq", sequenceName = "room_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @Column(name = "price_per_day", nullable = false)
    BigDecimal pricePerDay;

    @Column(name = "is_locked", nullable = false)
    Boolean isLocked;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    RoomType roomTypeId;

}
