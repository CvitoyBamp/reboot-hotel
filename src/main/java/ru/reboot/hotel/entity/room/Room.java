package ru.reboot.hotel.entity.room;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "room")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @NonNull
    @Column(name = "price_per_day", nullable = false)
    BigDecimal pricePerDay;

    @NonNull
    @Column(name = "is_locked", nullable = false)
    Boolean isLocked;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = RoomType.class)
    RoomType roomType;

    @Transient
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "room")
    Booking booking;

}
