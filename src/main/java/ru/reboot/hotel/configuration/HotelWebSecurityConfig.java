package ru.reboot.hotel.configuration;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.reboot.hotel.service.user.CustomUserDetailsService;
import ru.reboot.hotel.service.user.HotelUserService;
import ru.reboot.hotel.service.user.RoleService;

/**
 * Config for Spring security
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class HotelWebSecurityConfig {

    private HotelUserService hotelUserService;

    private RoleService roleService;

    /**
     * Create Bean for encoding password
     * @return bcrypt encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Custom UserDetailsService realisation for Spring Security
     * @return CustomUserDetailsService for checking login
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(hotelUserService, roleService);
    }

    /**
     * Defines a filter chain which is capable of being matched against an {@code HttpSecurity}
     * @param http - HttpSecurity object for filtering requests
     * @return chain of http filters
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);

        return http.authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers("/register/**").anonymous()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
                                .requestMatchers("/reservation/**").hasRole("USER")
                                .requestMatchers("/**").permitAll()
                )
                .formLogin(form -> form
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .build();
    }

    /**
     * Defines an authentication provider.
     * @return Authenticates a user with the given request.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
