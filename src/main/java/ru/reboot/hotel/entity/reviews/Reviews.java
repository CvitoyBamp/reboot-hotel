package ru.reboot.hotel.entity.reviews;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reviews", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reviews extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_id_seq")
    @SequenceGenerator(name = "reviews_id_seq", sequenceName = "reviews_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @Column(name = "comment", nullable = false)
    String comment;

    @Column(name = "rating", columnDefinition = "SMALLINT CHECK (RATING BETWEEN 1 AND 5)")
    Short rating;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = HotelUser.class)
//    @JoinColumn(name = "user_id")
//    HotelUser hotelUserId;

    @Column(name = "user_id", nullable = false)
    Long userId;

}
