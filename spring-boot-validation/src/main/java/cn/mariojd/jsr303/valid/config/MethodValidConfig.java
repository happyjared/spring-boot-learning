package cn.mariojd.jsr303.valid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author Jared
 * @date 2019/1/15 20:02
 */
@Configuration
public class MethodValidConfig {

    /**
     * 配置MethodValidationPostProcessor拦截器，以实现对方法参数的校验
     *
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
