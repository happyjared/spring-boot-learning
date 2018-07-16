package cn.mariojd.springboot.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.mariojd.springboot.shardingjdbc.demo.mapper")
public class SpringbootShardingjdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShardingjdbcDemoApplication.class, args);
    }
}
