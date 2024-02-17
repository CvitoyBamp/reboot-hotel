package ru.reboot.hotel.service.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.user.HotelUserRepository;
import ru.reboot.hotel.utils.security.CustomUserDetails;
import ru.reboot.hotel.utils.security.SecurityUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private HotelUserService hotelUserService;
    private RoleService roleService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        HotelUser hotelUser = hotelUserService.findHotelUserByEmail(email);

        Set<GrantedAuthority> authorities = roleService.getRoles().stream().
                map(roles -> SecurityUtil.convertToAuthority(roles.getRoleName()))
                .collect(Collectors.toSet());

        return new CustomUserDetails(hotelUser, authorities);

    }
}
