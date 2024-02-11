package ru.reboot.hotel.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.entity.reviews.Reviews;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.utils.annotations.ValidLogin;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelUser extends AuditEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @EqualsAndHashCode.Include
    @NotEmpty(message = "Имя не должно быть пустым")
    @Column(name = "name", nullable = false, length = 128)
    String name;

    @EqualsAndHashCode.Include
    @NotEmpty(message = "Пароль не может быть пустым")
    @Column(name = "password", nullable = false)
    String password;

    @EqualsAndHashCode.Include
    @NotBlank(message = "Заполните имеил")
    @Email
    @Column(name = "email", nullable = false, length = 320)
    String email;

    @EqualsAndHashCode.Include
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday", nullable = false)
    LocalDate birthday;

    @EqualsAndHashCode.Include
    @NotNull
    @Pattern(regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$")
    @Max(12)
    @Column(name = "phone", nullable = false)
    String phone;

    @EqualsAndHashCode.Include
    @NotNull
    @Column(name = "role_id", nullable = false)
    Integer roleId;

    @OneToMany(mappedBy = "userId", targetEntity = Reviews.class)
    List<Reviews> reviewsList;

}
