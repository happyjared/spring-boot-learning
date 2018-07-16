package cn.mariojd.springboot.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.mariojd.springboot.shardingjdbc.mapper")
public class SpringBootShardingjdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShardingjdbcApplication.class, args);
    }
}
