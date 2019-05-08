package services;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"data.access.mapper"})
public class MusicServerServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicServerServicesApplication.class, args);
    }

}
