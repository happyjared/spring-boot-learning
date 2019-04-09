package cn.mariojd.cache.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Jared
 * @date 2019/4/9 9:01
 */
@Slf4j
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        // 类名.方法名.参数值
        String keySuffix = target.getClass().getSimpleName() + "." + method.getName() + "." + Arrays.toString(params);
        log.info("Cache key suffix : {}", keySuffix);
        return keySuffix;
    }

}
