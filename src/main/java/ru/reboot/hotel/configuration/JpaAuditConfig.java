package ru.reboot.hotel.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Config for AuditingEntityListener, auto time insert/update for row
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {
}
