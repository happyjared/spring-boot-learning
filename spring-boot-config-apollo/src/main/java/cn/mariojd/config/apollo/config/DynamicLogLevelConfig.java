package cn.mariojd.config.apollo.config;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author Jared
 * @date 2019/3/12 13:44
 */
@Slf4j
@Configuration
public class DynamicLogLevelConfig {

    @Resource
    private LoggingSystem loggingSystem;

    private static final String LOG_KEY = "logging.level";

    @ApolloConfigChangeListener
    private void configChangeListener(ConfigChangeEvent e) {
        e.changedKeys().stream().filter(key -> StringUtils.startsWithIgnoreCase(LOG_KEY, key)).forEach(key -> {
            ConfigChange change = e.getChange(key);
            String oleLevel = change.getOldValue();
            String newLevel = change.getNewValue();

            Arrays.stream(LogLevel.values()).filter(logLevel ->
                    StringUtils.startsWithIgnoreCase(newLevel, logLevel.toString())).findFirst().ifPresent(logLevel -> {
                loggingSystem.setLogLevel("cn.mariojd.config.apollo", logLevel);
            });

            log.info("动态调整日志级别：Key -> {} ; OldLevel -> {} ; NewLevel -> {}", key, oleLevel, newLevel);
        });
    }

}
