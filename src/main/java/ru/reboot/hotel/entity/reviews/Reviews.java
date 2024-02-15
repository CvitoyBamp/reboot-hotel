package ru.reboot.hotel.entity.reviews;

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
@Table(name = "reviews", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reviews extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_id_seq")
    @SequenceGenerator(name = "reviews_id_seq", sequenceName = "reviews_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @EqualsAndHashCode.Include
    @Column(name = "comment", nullable = false)
    String comment;

    @EqualsAndHashCode.Include
    @Column(name = "rating", columnDefinition = "SMALLINT CHECK (RATING BETWEEN 1 AND 5)")
    Short rating;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = HotelUser.class)
//    @JoinColumn(name = "user_id")
//    HotelUser hotelUserId;

    @EqualsAndHashCode.Include
    @Column(name = "user_id", nullable = false)
    Long userId;

}
