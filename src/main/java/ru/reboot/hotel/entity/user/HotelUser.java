package ru.reboot.hotel.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import ru.reboot.hotel.entity.booking.Booking;
import ru.reboot.hotel.utils.annotations.ValidLogin;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "user", schema = "public")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HotelUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq",  allocationSize=1)
    @Column(name = "id", nullable = false)
    Long id;

    @ValidLogin
    @Column(name = "name", nullable = false, length = 128)
    String name;

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
    Integer phone;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Booking booking;

}
