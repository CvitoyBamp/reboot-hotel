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
@Table(name = "booking", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    HotelUser userId;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Column(name = "room_id", nullable = false)
    Set<Room> roomsId = new HashSet<>();

    @EqualsAndHashCode.Include
    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    @EqualsAndHashCode.Include
    @Column(name = "end_date", nullable = false)
    LocalDate endDate;

    @EqualsAndHashCode.Include
    @Column(name = "note", nullable = false)
    String note;
}
