package cn.mariojd.actuator.support;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jared
 * @date 2019/4/11 17:09
 */
@Component
@Endpoint(id = "application")
public class CustomEndpoint {

    @Resource
    private Environment environment;

    @ReadOperation
    public Map<String, String> get() {
        Map<String, String> info = new HashMap<>(4);
        info.put("Application Name", environment.getProperty("spring.application.name", ""));
        info.put("Application Port", environment.getRequiredProperty("management.server.port"));
        return info;
    }

    @WriteOperation
    public void post(@Selector String arg) {
    }

    @DeleteOperation
    public void delete(@Selector String arg) {
    }

}
