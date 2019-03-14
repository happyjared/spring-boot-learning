package cn.mariojd.api.version.controller;

import cn.mariojd.api.version.annotation.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jared
 * @date 2019/3/14 13:45
 */
@Slf4j
@ApiVersion(2)
@RestController
@RequestMapping("api/{version}/test")
public class TestController {

    @GetMapping
    @ApiVersion
    public String test01(@PathVariable String version) {
        return "test01 : " + version;
    }

    @GetMapping
    public String test02(@PathVariable String version) {
        return "test02 : " + version;
    }

    @GetMapping
    @ApiVersion(3)
    public String test03(@PathVariable String version) {
        return "test03 : " + version;
    }

}
