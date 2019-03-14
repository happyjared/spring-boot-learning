package cn.mariojd.api.version.annotation;

import java.lang.annotation.*;

/**
 * @author Jared
 * @date 2019/3/14 13:43
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    /**
     * 标识版本号，从1开始
     */
    int value() default 1;

}
