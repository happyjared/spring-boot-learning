package cn.mariojd.springboot.attribute;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class AttributeApplication {

    private static final String UNDEFINED = "undefined";
    private static final String SPRING_BOOT_PREFIX = "spring-boot";
    private static final String APPLICATION_YML = "application.yml";
    private static final String APPLICATION_PROPERTIES = "application.properties";
    private static final String SPRING_BOOT_HELLO = "spring-boot.hello";
    private static final String SPRING_BOOT_STR_ARRAY = "spring-boot.str-array";
    private static final String SPRING_BOOT_INT_ARRAY = "spring-boot.int-array";

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AttributeApplication.class, args);
        AttributeApplication bean = applicationContext.getBean(AttributeApplication.class);
        bean.getAttrByValueAnnotation();
        bean.getAttrByEnvironment();
        System.out.println("2-2. 通过ApplicationContext获取Environment后再获取值: " + applicationContext
                .getEnvironment()
                .getProperty(SPRING_BOOT_HELLO, UNDEFINED));
        bean.getAttrByConfigurationPropertiesAnnotation();
        bean.getAttrByPropertiesLoaderUtils();
        bean.getArrayAttr();
        bean.getListAttr();
    }

    @Value("${" + SPRING_BOOT_HELLO + "}")
    private String hello;

    /**
     * 1. 通过@Value注解获取值
     */
    public void getAttrByValueAnnotation() {
        System.out.println("1. 通过@Value注解获取值: " + hello);
    }

    @Resource
    private Environment environment;

    /**
     * 2. 通过注入Environment获取值
     */
    public void getAttrByEnvironment() {
        String property = environment.getProperty(SPRING_BOOT_HELLO);
        System.out.println("2-1. 通过注入Environment获取值: " + property);
    }

    @Data
    @Component
    @PropertySource("classpath:" + APPLICATION_YML)
    @ConfigurationProperties(prefix = SPRING_BOOT_PREFIX)
    class Attribute {

        private String hello;
        private String world;

    }

    @Resource
    private Attribute attribute;

    /**
     * 3. 通过@ConfigurationProperties注入对象属性获取
     */
    public void getAttrByConfigurationPropertiesAnnotation() {
        System.out.println("3. 通过@ConfigurationProperties注入对象属性获取: " + attribute);
    }

    /**
     * 4. 通过PropertiesLoaderUtils获取(注意，此工具类仅可处理.properties或.xml配置文件)
     */
    public void getAttrByPropertiesLoaderUtils() {
        try {
            ClassPathResource resource = new ClassPathResource(APPLICATION_PROPERTIES);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            String property = properties.getProperty(SPRING_BOOT_HELLO, UNDEFINED);
            System.out.println("4. 通过PropertiesLoaderUtils获取: " + property);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attention : it is error if use Integer[]
     */
    @Value("${" + SPRING_BOOT_INT_ARRAY + "}")
    private int[] array;

    /**
     * 通过@Value注解获取数组
     */
    public void getArrayAttr() {
        System.out.println("5. 通过@Value注解获取数组: " + Arrays.toString(array));
    }

    @Value("#{'${" + SPRING_BOOT_STR_ARRAY + "}'.split(',')}")
    private List<String> list;

    /**
     * 通过@Value注解获取List
     */
    public void getListAttr() {
        System.out.println("6. 通过@Value注解获取List: " + list.toString());
    }

}
