package ru.reboot.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;


/**
 * main Application
 */

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HotelApplication.class, args);
    }

}
