package cn.mariojd.cache.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jared
 * @date 2019/4/9 9:24
 */
@Configuration
public class CustomConfig {

    @Bean
    public CustomKeyGenerator customKeyGenerator() {
        return new CustomKeyGenerator();
    }

}
