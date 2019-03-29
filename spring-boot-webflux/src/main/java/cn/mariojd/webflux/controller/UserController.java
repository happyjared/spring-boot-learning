package cn.mariojd.webflux.controller;

import cn.mariojd.webflux.entity.User;
import cn.mariojd.webflux.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/3/29 8:50
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("{id}")
    public Mono<User> get(@PathVariable int id) {
        return userService.get(id);
    }

}
