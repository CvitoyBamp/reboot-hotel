package ru.reboot.hotel.entity.roles;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import ru.reboot.hotel.entity.AuditEntity;
import ru.reboot.hotel.entity.user.HotelUser;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.time.LocalDate;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Roles extends AuditEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    Long id;

    @NonNull
    @EqualsAndHashCode.Include
    @Column(name = "role_name", columnDefinition = "VARCHAR(64) CHECK (role_name IN ('ADMIN', 'USER'))")
    String roleName;

    @Transient
    @OneToMany(mappedBy = "roles", targetEntity = HotelUser.class)
    Set<HotelUser> hotelUsers;

    @Override
    public String getAuthority() {
        return getRoleName();
    }
}
