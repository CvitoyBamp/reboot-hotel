package ru.reboot.hotel.entity.booking;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "booking")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = HotelUser.class)
    HotelUser hotelUser;

    @NonNull
    @EqualsAndHashCode.Include
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, targetEntity = Room.class)
    Room room;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "end_date", nullable = false)
    LocalDate endDate;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "note", nullable = false)
    String note;
}
