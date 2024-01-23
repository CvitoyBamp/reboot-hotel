package ru.reboot.hotel.entity.booking;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "booking", schema = "public")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_order_number_seq")
    @SequenceGenerator(name = "booking_order_number_seq", sequenceName = "booking_order_number_seq",  allocationSize=1)
    @Column(name = "order_number", nullable = false)
    Long orderNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    HotelUser userId;

    @OneToMany(mappedBy = "booking",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Column(name = "room_id", nullable = false)
    Set<Room> rooms = new HashSet<>();

    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    LocalDate endDate;
}
