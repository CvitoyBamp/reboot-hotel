package ru.reboot.hotel.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.utils.annotations.ValidLogin;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelUser extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "name", nullable = false, length = 128)
    String name;

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

    @Column(name = "role_id", nullable = false)
    Integer roleId;

    @OneToMany(mappedBy = "userId", targetEntity = Reviews.class)
    List<Reviews> reviewsList;

}
