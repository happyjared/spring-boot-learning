package cn.mariojd.session.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@Controller
@SpringBootApplication
@EnableRedisHttpSession
public class SpringBootSessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSessionRedisApplication.class, args);
    }

    private static final String ROLE = "Role";
    private static final String AUTHORIZATION = "Authorization";

    @Resource
    private HttpSession session;

    @GetMapping
    public String init() {
        if (StringUtils.isEmpty(session.getAttribute(AUTHORIZATION))) {
            return "redirect:/login";
        }
        return "redirect:/index";
    }

    @GetMapping("login")
    public String login() {
        session.setAttribute(AUTHORIZATION, session.getId());
        session.setAttribute(ROLE, new Random().nextInt(3));
        return "redirect:/index";
    }

    @GetMapping("logout")
    @ResponseBody
    public Map logout() {
        session.removeAttribute(session.getId());
        Map<String, Object> res = new HashMap<>(2);
        res.put(AUTHORIZATION, session.getAttribute(AUTHORIZATION));
        return res;
    }

    @GetMapping("index")
    @ResponseBody
    public Map index() {
        Map<String, Object> res = new HashMap<>(4);
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String s = attributeNames.nextElement();
            System.out.println("s = " + s);
        }
        res.put(ROLE, session.getAttribute(ROLE));
        res.put(AUTHORIZATION, session.getAttribute(AUTHORIZATION));
        return res;
    }

}
