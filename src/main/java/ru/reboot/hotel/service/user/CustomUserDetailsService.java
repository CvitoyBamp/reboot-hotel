package ru.reboot.hotel.service.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.user.HotelUserRepository;
import ru.reboot.hotel.utils.CustomUserDetails;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private HotelUserRepository hotelUserRepository;

    private PasswordEncoder passwordEncoder;

    private RoleService roleService;

    @Transactional
    public void createHotelUser(HotelUser hotelUser){
        hotelUser.setPassword(passwordEncoder.encode(hotelUser.getPassword()));
        hotelUserRepository.save(hotelUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        HotelUser hotelUser = hotelUserRepository.findCustomUserDetailsByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User don't found"));

        return new CustomUserDetails(hotelUser, roleService);
    }

    @Transactional(readOnly = true)
    public List<HotelUser> getAllUsers() {
        return hotelUserRepository.findAll();
    }


}
