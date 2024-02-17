package ru.reboot.hotel.entity.reviews;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.room.Room;
import ru.reboot.hotel.entity.user.HotelUser;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reviews")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reviews extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable=false, updatable=false)
    Long id;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "comment", nullable = false)
    String comment;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "rating", columnDefinition = "SMALLINT CHECK (RATING BETWEEN 1 AND 5)")
    Short rating;

    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = HotelUser.class)
    HotelUser hotelUser;

}
