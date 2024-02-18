package ru.reboot.hotel.entity.room;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.booking.Booking;

import java.math.BigDecimal;

/**
 * Entity Room for Table name = "room"
 */
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
