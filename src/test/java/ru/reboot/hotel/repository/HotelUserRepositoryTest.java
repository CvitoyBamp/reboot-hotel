package ru.reboot.hotel.repository;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.entity.user.HotelUser;
import ru.reboot.hotel.repository.user.HotelUserRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:/updateSequence.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class HotelUserRepositoryTest {

    HotelUser hotelUser;

    Roles role;

    @Autowired
    HotelUserRepository hotelUserRepository;

    @Autowired
    TestEntityManager testEntityManager;
    @BeforeEach
    void initHotelUser() {
        role = new Roles("USER");
        hotelUser = new HotelUser("Test", new BCryptPasswordEncoder().encode("123456"), "test@bk.ru", LocalDate.of(1997, 3, 13), "80001001010", role);
    }

    @AfterEach
    void deleteHotelUser() {
        testEntityManager.detach(hotelUser);
    }

    @Test
    @DisplayName("Find hotel user by email")
    void findUserByEmail() {
        hotelUserRepository.save(hotelUser);
        assertThat(testEntityManager.find(HotelUser.class, hotelUser.getId())).isEqualTo(hotelUserRepository.findHotelUsersByEmail(hotelUser.getEmail()).get());
    }

    @Test
    @DisplayName("No user with such email")
    void cantFindUserByEmail() {
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(() -> hotelUserRepository.findHotelUsersByEmail("noExistTest@ya.ru").orElseThrow());
    }

    @Test
    @DisplayName("Add new user")
    void addUser() {
        hotelUserRepository.save(hotelUser);
        assertThat(testEntityManager.find(HotelUser.class, hotelUser.getId())).isEqualTo(hotelUser);
    }
}
