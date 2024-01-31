package ru.reboot.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class HotelApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HotelApplication.class, args);


//        File fi = new File("./src/main/resources/static/images");
//        int index = 0;
//        for (final File ph: fi.listFiles()) {
//            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(ImageIO.read(new File(ph.getAbsolutePath())), "jpg", bos);
//            System.out.println("insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('" + (index++) + "','" + ph.getName() + "','" + bos.toByteArray() + "');");
//        }
    }

}
