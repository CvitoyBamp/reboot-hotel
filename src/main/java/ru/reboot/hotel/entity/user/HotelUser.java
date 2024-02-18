package ru.reboot.hotel.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.roles.Roles;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelUser extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "name", nullable = false, length = 128)
    String name;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "password", nullable = false)
    String password;

    @NonNull
    @EqualsAndHashCode.Include
    @Email
    @Column(name = "email", nullable = false, length = 320)
    String email;

    @NonNull
    @EqualsAndHashCode.Include
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday", nullable = false)
    LocalDate birthday;

    @EqualsAndHashCode.Include
    @NonNull
    @Pattern(regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$")
    @Max(12)
    @Column(name = "phone", nullable = false)
    String phone;

    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Roles.class)
    Roles roles;

    @Transient
    @OneToMany(mappedBy = "hotelUser", targetEntity = Reviews.class)
    Set<Reviews> reviewsList;

    @Transient
    @OneToMany(mappedBy = "hotelUser", targetEntity = Booking.class)
    Set<Booking> bookings;

    public HotelUser(Long id, @NonNull String name, @NonNull String email, @NonNull LocalDate birthday, @NonNull String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public Roles getRoles() {
        return roles;
    }

    public Set<Reviews> getReviewsList() {
        return reviewsList;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

}
