package cn.mariojd.config.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@EnableApolloConfig
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);

        // 监听配置变化事件
        Config config = ConfigService.getAppConfig();
        config.addChangeListener(changeEvent -> {
            log.debug("Changes for namespace {}", changeEvent.getNamespace());
            for (String key : changeEvent.changedKeys()) {
                ConfigChange change = changeEvent.getChange(key);
                log.info(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
                        change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
            }
        });

        testDynamicLogLevelConfig();
    }

    public static void testDynamicLogLevelConfig() {
        for (; ; ) {
            log.debug("Debug log...");
            log.info("Info log...");
            log.warn("Warn log...");
            log.error("Error log...");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
