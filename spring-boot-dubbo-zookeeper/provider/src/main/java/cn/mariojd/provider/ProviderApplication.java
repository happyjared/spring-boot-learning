package cn.mariojd.provider;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ProviderApplication.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
//		SpringApplication.run(ProviderApplication.class, args);
    }
}
