package cn.mariojd.jetcache;

import cn.mariojd.jetcache.entity.Coffee;
import cn.mariojd.jetcache.repository.CoffeeRepository;
import cn.mariojd.jetcache.service.CoffeeCreateCacheService;
import cn.mariojd.jetcache.service.CoffeeMethodCacheService;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * 这里 @EnableMethodCache，@EnableCreateCacheAnnotation 分别用于激活 @Cached 和 @CreateCache 注解
 */
@Slf4j
@EnableMethodCache(basePackages = "cn.mariojd.jetcache")
@EnableCreateCacheAnnotation
@SpringBootApplication
public class SpirngBootJetcacheApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpirngBootJetcacheApplication.class, args);
    }

    @Resource
    private CoffeeRepository coffeeRepository;

    @Resource
    private CoffeeCreateCacheService coffeeCreateCacheService;

    @Resource
    private CoffeeMethodCacheService coffeeMethodCacheService;

    @Override
    public void run(ApplicationArguments args) {
        // Test Coffee create cache

        Coffee latte = Coffee.builder().name("Latte").price(20.0f).build();
        coffeeCreateCacheService.add(latte);
        int id = latte.getId();

        coffeeCreateCacheService.get(id);
        latte.setPrice(25.0f);
        coffeeCreateCacheService.update(latte);
        coffeeCreateCacheService.delete(id);

        log.info("\n");

        // Test Coffee method cache

        Coffee cappuccino = Coffee.builder().name("Cappuccino").price(30.0f).build();
        coffeeRepository.saveCoffee(latte);

        id = cappuccino.getId();
        coffeeMethodCacheService.get(id);
        cappuccino.setPrice(25.0f);
        coffeeMethodCacheService.update(cappuccino);
        coffeeMethodCacheService.delete(id);
    }
}
