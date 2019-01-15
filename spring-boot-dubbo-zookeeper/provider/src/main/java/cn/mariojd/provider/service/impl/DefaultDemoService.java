package cn.mariojd.provider.service.impl;

import cn.mariojd.api.service.DemoService;
import com.alibaba.dubbo.config.annotation.Service;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DefaultDemoService implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello , " + name;
    }

}
