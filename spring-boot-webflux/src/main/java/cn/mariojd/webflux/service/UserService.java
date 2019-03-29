package cn.mariojd.webflux.service;

import cn.mariojd.webflux.entity.User;
import cn.mariojd.webflux.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/3/29 8:50
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    public Mono<User> get(int id) {
        return userRepository.findById(id);
    }

}
