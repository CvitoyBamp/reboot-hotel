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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "price_per_day", nullable = false)
    BigDecimal pricePerDay;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "is_locked", nullable = false)
    Boolean isLocked;

    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = RoomType.class)
    RoomType roomType;

    @Transient
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "room")
    Booking booking;

}
