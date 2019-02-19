package cn.mariojd.jpa.naming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.security.auth.login.Configuration;

@SpringBootApplication
public class SpringBootJpaPhysicalNamingApplication {

    public static void main(String[] args) {
        // SpringApplication.run(SpringBootJpaPhysicalNamingApplication.class, args);
        SpringApplication application = new SpringApplication(SpringBootJpaPhysicalNamingApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

}
