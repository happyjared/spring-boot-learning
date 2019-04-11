package cn.mariojd.session.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@SpringBootApplication
@EnableRedisHttpSession
public class SpringBootSessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSessionRedisApplication.class, args);
    }

    @GetMapping("login")
    public Map login(HttpSession session) {
        if (session.getAttribute("auth") == null) {
            session.setAttribute("auth", new Random().nextInt(1000000));
        }
        Map<String, Object> res = new HashMap<>(2);
        res.put("msg", "登录成功");
        return res;
    }

    @GetMapping("api/user/{id}")
    public Map user(@PathVariable int id, @SessionAttribute String session) {
        log.info(session);
        Map<String, Object> res = new HashMap<>(2);
        res.put("msg", id);
        return res;
    }

}
