package services;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"data.access.repositories","data.access.mapper","services"})
@MapperScan(basePackages = {"data.access.mapper"})
//@EnableWebMvc

public class MusicServerServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicServerServicesApplication.class, args);
    }

}
