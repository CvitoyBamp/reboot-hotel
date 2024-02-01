package ru.reboot.hotel.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.utils.annotations.ValidLogin;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "user", schema = "public")
@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelUser extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false, length = 128)
    String name;

    @Column(name = "photo", nullable = false)
    String photo;

    @Column(name = "password", nullable = false)
    String password;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, length = 320)
    String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday", nullable = false)
    LocalDate birthday;

    @NotNull
    @Pattern(regexp = "[0-9.\\-() x/+]+")
    @Max(12)
    @Column(name = "phone", nullable = false)
    String phone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Roles roles;

}
