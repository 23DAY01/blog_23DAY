package site.day.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Blog23DayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Blog23DayApplication.class, args);
    }

}
