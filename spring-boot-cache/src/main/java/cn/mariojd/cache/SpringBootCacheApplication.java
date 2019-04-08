package cn.mariojd.cache;

import cn.mariojd.cache.entity.User;
import cn.mariojd.cache.repository.UserRepository;
import cn.mariojd.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableCaching
@SpringBootApplication
public class SpringBootCacheApplication implements ApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(SpringBootCacheApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
        log.info("\n");
    }

    @Resource
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        // 初始化数据
        for (int i = 0; i < 10; i++) {
            User user = User.builder().name("ZS" + i).build();
            userRepository.save(user);
        }
    }

    @Resource
    private UserService userService;

    @Resource
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {
        // 测试缓存，观察是否有SQL输出

        PageRequest pageable = PageRequest.of(0, 5);
        userService.page(pageable);
        for (int i = 0; i < 5; i++) {
            userService.page(pageable);
            log.info("Reading page cache...");
        }
        // 配置5秒中后缓存失效，重新读取
        TimeUnit.MILLISECONDS.sleep(Integer.parseInt(environment.getProperty("spring.cache.redis.time-to-live", "5000")));
        log.warn("Page Cache expired : " + userService.page(pageable).getTotalElements());

        log.info("\n");

        // Test CRUD Cache
        User user = userService.add("李四");
        int userId = user.getId();
        userService.get(userId);
        log.info("Reading user cache..." + userService.get(userId));

        userService.update(userId, "王五");
        log.info("Reading new user cache..." + userService.get(userId));

        userService.delete(userId);
        log.warn("User Cache delete : " + userService.get(userId));
    }
}
