package ru.reboot.hotel.entity.roles;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.user.HotelUser;

import java.time.LocalDate;

@Entity
@Table(name = "roles", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Roles extends AuditEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq",  allocationSize=1)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    Long id;

    @EqualsAndHashCode.Include
    @Column(name = "role_name", columnDefinition = "VARCHAR(64) CHECK (role_name IN ('ADMIN', 'USER'))")
    String roleName;

    @EqualsAndHashCode.Include
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = HotelUser.class)
    @JoinColumn(name = "id")
    HotelUser hotelUser;

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
