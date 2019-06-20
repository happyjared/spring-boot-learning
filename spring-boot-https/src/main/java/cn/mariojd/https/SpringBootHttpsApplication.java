package cn.mariojd.https;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringBootHttpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHttpsApplication.class, args);
    }

}
