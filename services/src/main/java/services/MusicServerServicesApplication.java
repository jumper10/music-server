package services;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("data.access.mapper")
public class MusicServerServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicServerServicesApplication.class, args);
    }

}
