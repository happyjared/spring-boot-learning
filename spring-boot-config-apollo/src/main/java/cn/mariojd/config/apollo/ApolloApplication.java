package cn.mariojd.config.apollo;

import cn.mariojd.config.apollo.repository.UserRepository;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableApolloConfig
@SpringBootApplication
public class ApolloApplication implements InitializingBean {

    @Resource
    private UserRepository userRepository;

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
        System.out.println("ApolloApplication started...");

        // 监听配置变化事件
        Config config = ConfigService.getAppConfig();
        config.addChangeListener(changeEvent -> {
            log.debug("Changes for namespace {}", changeEvent.getNamespace());
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
                        change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
            }
        });
    }

    @Override
    public void afterPropertiesSet() {
        executorService.submit(this::testDynamicLogLevelConfig);
        executorService.submit(this::testDynamicDataSourceConfig);
    }

    public void testDynamicLogLevelConfig() {
        // 默认 INFO 级别下程序不输出 DEBUG 日志
        for (; ; ) {
            log.debug("Debug log...");
            log.info("Info log...");
            log.warn("Warn log...");
            log.error("Error log...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testDynamicDataSourceConfig() {
        for (; ; ) {
            try {
                userRepository.findAll().forEach(user -> log.info("User: {}", user));
                TimeUnit.SECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
