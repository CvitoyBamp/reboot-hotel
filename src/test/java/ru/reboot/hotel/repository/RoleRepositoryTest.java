package ru.reboot.hotel.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import ru.reboot.hotel.entity.roles.Roles;
import ru.reboot.hotel.repository.user.RolesRepository;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:/updateSequence.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class RoleRepositoryTest {

    Roles role;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    TestEntityManager testEntityManager;
    @BeforeEach
    void initRole() {
        role = new Roles("USER");
    }

    @AfterEach
    void deleteHotelUser() {
        testEntityManager.detach(role);
    }

    @Test
    @DisplayName("Add new role")
    void addRole() {
        rolesRepository.save(role);
        assertThat(testEntityManager.find(Roles.class, role.getId())).isEqualTo(role);
    }
}
